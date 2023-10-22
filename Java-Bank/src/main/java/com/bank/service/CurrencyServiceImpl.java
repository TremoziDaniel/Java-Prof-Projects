package com.bank.service;

import com.bank.domain.entity.Currency;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    private final CurrencyRepository repository;

    public CurrencyServiceImpl(CurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Currency> getAll() {
        List<Currency> currencies = repository.findAll();
        if (currencies.isEmpty()) {
            throw new ItemNotFoundException("Currencies");
        }

        return currencies;
    }

    @Override
    public Currency getById(int id) {
        return repository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Currency %d", id)));
    }

    @Override
    public Currency create(Currency currency) {
        return repository.save(currency);
    }

    @Override
    public Currency update(int id, Currency currency) {
        currency.setId(id);

        return repository.save(currency);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Currency changeRate(int id, BigDecimal rate) {
        Currency currency = getById(id);
        currency.setRate(rate);

        return currency;
    }

    @Override
    public BigDecimal convertCurrency(int currencyOriginalId, int currencyConverterId, BigDecimal amount) {
        Currency currencyOriginal = getById(currencyConverterId);
        Currency currencyConverter = getById(currencyConverterId);
        amount = amount.divide(currencyOriginal.getRate()).multiply(currencyConverter.getRate())
                .setScale(2, RoundingMode.HALF_EVEN);

        return amount;
    }

    @Override
    public Currency getCurrencyByAbb(String abb) {
        List<Currency> currencies = getAll();

        return currencies.stream().filter(cur -> abb.equals(cur.getCurrencyAbb()))
                .findAny().orElseThrow(() -> new ItemNotFoundException("Currency"));
    }
}
