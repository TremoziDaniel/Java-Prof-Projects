package com.bank.service;

import com.bank.domain.entity.Currency;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyService {

    List<Currency> getAll();

    Currency getById(Integer id);

    Currency create(Currency currency);

    Currency update(Integer id, Currency currency);

    void delete(Integer id);

    Currency changeRate(Integer id, BigDecimal rate);

    BigDecimal convertCurrency(Currency currencyOriginal, Currency currencyConverted, BigDecimal amount);

    Currency getByAbb(String abb);
}
