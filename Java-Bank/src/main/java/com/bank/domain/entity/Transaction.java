package com.bank.domain.entity;

import com.bank.domain.enums.TransactionType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "debit_account_id", referencedColumnName = "id")
    private Account creditAccount;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_account_id", referencedColumnName = "id")
    private Account debitAccount;

    @Enumerated(value = EnumType.STRING)
    private TransactionType type;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    private BigDecimal amount;

    private String description;

    private LocalDateTime completedAt;

    public Transaction() {
    }

    public Transaction(long id, Account debitAccount, Account creditAccount, TransactionType type,
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

    public Transaction(Account debitAccount, Account creditAccount, TransactionType type,
                       Currency currency, BigDecimal amount, String description, LocalDateTime completedAt) {
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

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
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
        return "Transaction{" +
                "id=" + id +
                ", creditAccount=" + creditAccount +
                ", debitAccount=" + debitAccount +
                ", type=" + type +
                ", currency=" + currency +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", completedAt=" + completedAt +
                '}';
    }
}