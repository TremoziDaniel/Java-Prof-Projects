package com.bank.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    private LocalDateTime completeTime;

    private String transactionType;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="account_id_from", referencedColumnName = "id")
    private Account accountIdFrom;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="account_id_to", referencedColumnName = "id")
    private Account accountIdTo;

    private Double moneyTransacted;

    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="currency_id", referencedColumnName = "id")
    private Currency currency;
}