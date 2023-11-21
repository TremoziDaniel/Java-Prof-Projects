package com.bank.service;

import com.bank.domain.entity.Transaction;
import com.bank.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    List<Transaction> getAll();

    Transaction getById(Long id);

    void delete(Long id);

    Transaction transfer(String creditAccId, String debitAccId, BigDecimal amount,
                         String description, TransactionType type);
}
