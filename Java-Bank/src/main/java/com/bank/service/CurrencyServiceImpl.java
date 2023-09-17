package com.bank.service;

import com.bank.domain.entity.Currency;
import com.bank.domain.exception.InvalidArgumentException;
import com.bank.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    @Autowired
    private CurrencyRepository repository;

    @Override
    public List<Currency> getAll() {
        return repository.findAll();
    }

    @Override
    public Currency getById(long id) {
        return repository.findById(id).orElseThrow(() -> new InvalidArgumentException("Currency"));
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
}