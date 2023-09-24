package com.bank.service;

import com.bank.domain.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    public List<Account> getAll();

    public Account getById(String id);

    public Account create(Account account);

    public Account update(String id, Account account);

    public void delete(String id);

    public BigDecimal getBalance(String id);

    public void changeStatus(String id);

    public Account changeCurrency(String id, long currencyID);

    public Account topUp(String id, BigDecimal amount);
}