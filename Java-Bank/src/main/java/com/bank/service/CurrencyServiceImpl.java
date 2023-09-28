package com.bank.service;

import com.bank.domain.entity.Currency;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    private final CurrencyRepository repository;

    public CurrencyServiceImpl(CurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Currency> getAll() {
        return repository.findAll();
    }

    @Override
    public Currency getById(long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException("Currency"));
    }

    @Override
    public Currency create(Currency currency) {
        return repository.save(currency);
    }

    @Override
    public Currency update(long id, Currency currency) {
        currency.setId(id);

        return repository.save(currency);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Currency changeRate(long id, BigDecimal rate) {
        Currency currency = getById(id);
        currency.setRate(rate);

        return currency;
    }

    @Override
    public BigDecimal convertCurrency(long currencyOriginalId, long currencyConverterId, BigDecimal amount) {
        Currency currencyOriginal = getById(currencyConverterId);
        Currency currencyConverter = getById(currencyConverterId);
        amount = amount.divide(currencyOriginal.getRate()).multiply(currencyConverter.getRate())
                .setScale(2, RoundingMode.HALF_EVEN);

        return amount;
    }
}