package com.bank.domain.dto;

import com.bank.domain.entity.Currency;
import com.bank.domain.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    private long id;

    private String creditAccountName;

    private String debitAccountName;

    private TransactionType type;

    private String currencyAbb;

    private BigDecimal amount;

    private String description;

    private LocalDateTime completedAt;

    public TransactionDto() {
    }

    public TransactionDto(long id, String creditAccountName, String debitAccountName, TransactionType type,
                          String currencyAbb, BigDecimal amount, String description, LocalDateTime completedAt) {
        this.id = id;
        this.creditAccountName = creditAccountName;
        this.debitAccountName = debitAccountName;
        this.type = type;
        this.currencyAbb = currencyAbb;
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

    public String getCreditAccountName() {
        return creditAccountName;
    }

    public void setCreditAccountName(String creditAccountName) {
        this.creditAccountName = creditAccountName;
    }

    public String getDebitAccountName() {
        return debitAccountName;
    }

    public void setDebitAccountName(String debitAccountName) {
        this.debitAccountName = debitAccountName;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getCurrencyAbb() {
        return currencyAbb;
    }

    public void setCurrencyAbb(String currencyAbb) {
        this.currencyAbb = currencyAbb;
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
                ", creditAccount='" + creditAccountName + '\'' +
                ", debitAccount='" + debitAccountName + '\'' +
                ", type=" + type +
                ", currency='" + currencyAbb + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", completedAt=" + completedAt +
                '}';
    }
}