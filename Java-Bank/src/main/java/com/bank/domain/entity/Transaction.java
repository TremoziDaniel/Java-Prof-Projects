package com.bank.domain.entity;

import com.bank.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "debit_account_id", referencedColumnName = "id")
    private Account creditAccount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "credit_account_id", referencedColumnName = "id")
    private Account debitAccount;

    @Enumerated(value = EnumType.STRING)
    private TransactionType type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    private BigDecimal amount;

    private String description;

    private LocalDateTime completedAt;

    public Transaction(Account creditAccount, Account debitAccount, TransactionType type, Currency currency,
                       BigDecimal amount, String description, LocalDateTime completedAt) {
        this.creditAccount = creditAccount;
        this.debitAccount = debitAccount;
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.description = description;
        this.completedAt = completedAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
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
