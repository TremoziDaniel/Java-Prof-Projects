package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Currency;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountSeviceImpl implements AccountService {

    // Ask about required services, their validity(update, remove etc), return type, Bad requests instead exceptions
    private final AccountRepository repository;

    @Autowired
    private CurrencyService currencyService;

    public AccountSeviceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public Account getById(String id) {
        return repository.findById(UUID.fromString(id)).orElseThrow(() -> new ItemNotFoundException("Account"));
    }

    @Override
    public Account create(Account account) {
        return repository.save(account);
    }

    @Override
    public Account update(String id, Account account) {
        account.setId(UUID.fromString(id));

        return repository.save(account);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public BigDecimal getBalance(String id) {
        return getById(id).getBalance();
    }

    @Override
    public void changeStatus(String id) {
        Account account = getById(id);
        account.setStatus(!account.isStatus());
        repository.save(account);
    }

    @Override
    public Account changeCurrency(String id, long currencyID) {
        Account account = getById(id);

        if (account.isStatus()) {
            Currency currency = currencyService.getById(currencyID);
            account.setBalance(currencyService.convertCurrency(
                    account.getCurrency().getId(), currency.getId(), account.getBalance()));
            account.setCurrency(currency);

            return account;
        }  else {
            throw new EntityNotAvailableException(String.format("account %s is not available", id));
        }
    }

    @Override
    public Account topUp(String id, BigDecimal amount) {
        Account account = getById(id);

        if (account.isStatus()) {
            account.setBalance(account.getBalance().add(amount));

            return repository.save(account);
        } else {
            throw new EntityNotAvailableException(String.format("account %s is not available", id));
        }
    }
}