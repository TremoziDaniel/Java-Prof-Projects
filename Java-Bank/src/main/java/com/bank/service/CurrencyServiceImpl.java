package com.bank.service;

import com.bank.domain.entity.Currency;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.CurrencyRepository;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService{

    private final CurrencyRepository repository;

    @Override
    public List<Currency> getAll() {
        List<Currency> currencies = repository.findAll();
        if (currencies.isEmpty()) {
            throw new EntityNotFoundException("Currencies");
        }

        return currencies;
    }

    @Override
    public Currency getById(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Currency %d", id)));
    }

    @Override
    public Currency create(@Valid Currency currency) {
        return repository.save(currency);
    }

    @Override
    public Currency update(Integer id, @Valid Currency currency) {
        Currency oldCurrency = getById(id);
        currency.setId(oldCurrency.getId());

        return repository.save(currency);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Currency changeRate(Integer id, BigDecimal rate) {
        Currency currency = getById(id);
        currency.setRate(rate);

        return currency;
    }

    @Override
    public BigDecimal convertCurrency(Currency currencyOriginal, Currency currencyConverted, BigDecimal amount) {
        BigDecimal convertedAmount = amount.divide(currencyOriginal.getRate(), 4, RoundingMode.HALF_EVEN)
                .multiply(currencyConverted.getRate()).setScale(2, RoundingMode.HALF_EVEN);

        if (convertedAmount.compareTo(BigDecimal.ZERO) == 0) {
            throw new ValueException(String.format("Converted %s %s is lover than 0.01 %s",
                    amount, currencyOriginal.getCurrencyAbb(), currencyConverted.getCurrencyAbb()));
        }

        return convertedAmount;
    }

    @Override
    public Currency getByAbb(String abb) {
        return repository.findByCurrencyAbb(abb);
    }
}
