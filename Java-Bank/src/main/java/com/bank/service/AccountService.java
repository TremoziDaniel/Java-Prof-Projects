package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account getById(String id);

    Account create(String clientId, String currencyAbb, Account account);

    Account update(String iban, Account account);

    void delete(String id);

    List<Transaction> getTransactions(String iban);

    Account changeStatus(String iban);

    Account changeCurrency(String iban, String currencyAbb);

    Account topUp(String id, BigDecimal amount);

    Account getByIban(String iban);
}
