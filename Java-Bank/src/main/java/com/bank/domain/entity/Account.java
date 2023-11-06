package com.bank.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Getter
@Setter
public class Account {
    // @Basic
    // @NotBlank, @JsonFormat, @JsonIgnore(@JsonBackReference, @JsonManagedReference)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Pattern(message = "Invalid iban.\nExample: BI12A1B212345671234567812345678 or BI12A1B21234567",
            regexp = "^[A-Z]{2}[0-9]{2}[A-Z0-9]{4}[0-9]{7}([0-9]?){0,16}$")
    private String iban;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Size(message = "Account name length must be between 3 and 255 characters.",
            min = 3, max = 255)
    private String name;

    @Basic
    private boolean status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    private BigDecimal balance;

    @OneToMany(mappedBy = "creditAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactionsCredit = new ArrayList<>();

    @OneToMany(mappedBy = "debitAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactionsDebit = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Account(UUID id, String iban, Client client, String name, boolean status,
                   Currency currency, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (id == null) {
            this.id = UUID.randomUUID();
        } else {
            this.id = id;
        }
        this.iban = iban;
        this.client = client;
        this.name = name;
        this.status = status;
        this.currency = currency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public List<Transaction> compactTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(transactionsCredit);
        transactions.addAll(transactionsDebit);
        transactions.sort(Comparator.comparing(Transaction::getCompletedAt));

        return transactions;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", client=" + client +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", currency=" + currency +
                ", balance=" + balance +
                ", transactions=" + compactTransactions().stream().map(Transaction::getId).collect(Collectors.toList()) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
