package com.bank.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private String currencyName;

    private String currencyAbb;

    private BigDecimal rate;

    public Currency() {
    }

    public Currency(long id, String currencyName, String currencyAbb, BigDecimal rate) {
        this.id = id;
        this.currencyName = currencyName;
        this.currencyAbb = currencyAbb;
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyAbb() {
        return currencyAbb;
    }

    public void setCurrencyAbb(String currencyAbb) {
        this.currencyAbb = currencyAbb;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", currencyName='" + currencyName + '\'' +
                ", currencyAbb='" + currencyAbb + '\'' +
                ", rate=" + rate +
                '}';
    }
}