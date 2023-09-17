package com.bank.service;

import com.bank.domain.entity.Currency;

import java.util.List;

public interface CurrencyService {

    public List<Currency> getAll();

    public Currency getById(long id);

    public Currency create(Currency currency);

    public Currency update(long id, Currency currency);

    public void delete(long id);
}