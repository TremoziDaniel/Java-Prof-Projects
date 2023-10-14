package com.bank.service;

import com.bank.domain.entity.Currency;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyService {

    public List<Currency> getAll();

    public Currency getById(int id);

    public Currency create(Currency currency);

    public Currency update(int id, Currency currency);

    public void delete(int id);

    public Currency changeRate(int id, BigDecimal rate);

    public BigDecimal convertCurrency(int currencyOriginal, int currencyConverter, BigDecimal amount);

    public Currency getCurrencyByAbb(String abb);
}