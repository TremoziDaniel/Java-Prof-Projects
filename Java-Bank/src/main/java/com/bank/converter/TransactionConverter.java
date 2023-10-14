package com.bank.converter;

import com.bank.domain.dto.TransactionDto;
import com.bank.domain.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter implements EntityConverter<Transaction, TransactionDto> {
    @Override
    public TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(transaction.getId(), transaction.getDebitAccount().getName(),
                transaction.getCreditAccount().getName(), transaction.getType(),
                transaction.getCurrency().getCurrencyAbb(), transaction.getAmount(),
                transaction.getDescription(), transaction.getCompletedAt());
    }

    @Override
    public Transaction toEntity(TransactionDto transactionDto) {
        return null;
    }
}