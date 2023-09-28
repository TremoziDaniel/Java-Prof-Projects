package com.bank.converter;

import com.bank.domain.dto.TransactionDto;
import com.bank.domain.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter implements EntityConverter<Transaction, TransactionDto>{
    @Override
    public TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(transaction.getId(),
                new AccountConverter().toDto(transaction.getDebitAccount()),
                new AccountConverter().toDto(transaction.getCreditAccount()),
                transaction.getType(), transaction.getCurrency(),
                transaction.getAmount(), transaction.getDescription(), transaction.getCompletedAt());
    }

    @Override
    public Transaction toEntity(TransactionDto transactionDto) {
        return new Transaction(transactionDto.getId(),
                new AccountConverter().toEntity(transactionDto.getDebitAccount()),
                new AccountConverter().toEntity(transactionDto.getCreditAccount()),
                transactionDto.getType(), transactionDto.getCurrency(), transactionDto.getAmount(),
                transactionDto.getDescription(), transactionDto.getCompletedAt());
    }
}