package com.bank.domain.dto;

import java.math.BigDecimal;

public class CurrencyDto {

    private long id;

    private String currencyName;

    private String currencyAbb;

    private BigDecimal rate;

    public CurrencyDto() {
    }

    public CurrencyDto(long id, String currencyName, String currencyAbb, BigDecimal rate) {
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
        return "CurrencyDto{" +
                "id=" + id +
                ", currencyName='" + currencyName + '\'' +
                ", currencyAbb='" + currencyAbb + '\'' +
                ", rate=" + rate +
                '}';
    }
}