package com.bank.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String currencyName;

    private String currencyAbb;

    private Double rate;
}