package com.bank.domain.dto;

import com.bank.domain.entity.Currency;
import com.bank.domain.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    private long id;

    private AccountDto creditAccount;

    private AccountDto debitAccount;

    private TransactionType type;

    private Currency currency;

    private BigDecimal amount;

    private String description;

    private LocalDateTime completedAt;

    public TransactionDto() {
    }

    public TransactionDto(long id, AccountDto creditAccount, AccountDto debitAccount, TransactionType type,
                          Currency currency, BigDecimal amount, String description, LocalDateTime completedAt) {
        this.id = id;
        this.creditAccount = creditAccount;
        this.debitAccount = debitAccount;
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.description = description;
        this.completedAt = completedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountDto getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(AccountDto debitAccount) {
        this.debitAccount = debitAccount;
    }

    public AccountDto getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(AccountDto creditAccount) {
        this.creditAccount = creditAccount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "id=" + id +
                ", creditAccount=" + creditAccount.getId() +
                ", debitAccount=" + debitAccount.getId() +
                ", type=" + type +
                ", currency=" + currency +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", completedAt=" + completedAt +
                '}';
    }
}