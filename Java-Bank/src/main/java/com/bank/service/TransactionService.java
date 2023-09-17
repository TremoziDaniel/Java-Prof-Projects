package com.bank.service;

import com.bank.domain.entity.Transaction;

import java.util.List;

public interface TransactionService {

    public List<Transaction> getAll();

    public Transaction getById(long id);

    public Transaction create(Transaction transaction);

    public Transaction update(long id, Transaction transaction);

    public void delete(long id);
}