package com.bank.service;

import com.bank.domain.entity.Transaction;
import com.bank.domain.exception.InvalidArgumentException;
import com.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public List<Transaction> getAll() {
        return repository.findAll();
    }

    @Override
    public Transaction getById(long id) {
        return repository.findById(id).orElseThrow(() -> new InvalidArgumentException("Transaction"));
    }

    @Override
    public Transaction create(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public Transaction update(long id, Transaction transaction) {
        transaction.setId(id);

        return repository.save(transaction);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}