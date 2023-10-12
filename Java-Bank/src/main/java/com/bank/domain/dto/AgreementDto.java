package com.bank.domain.dto;

import com.bank.domain.entity.Currency;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgreementDto {

    private long id;

    private String accountName;

    private String productName;

    private Double interestRate;

    private boolean status;

    private Currency currency;

    private BigDecimal sum;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public AgreementDto() {
    }

    public AgreementDto(long id, String accountName, String productName, Double interestRate,boolean status,
                        Currency currency, BigDecimal sum, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.accountName = accountName;
        this.productName = productName;
        this.interestRate = interestRate;
        this.status = status;
        this.currency = currency;
        this.sum = sum;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "AgreementDto{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", productName='" + productName + '\'' +
                ", interestRate=" + interestRate +
                ", status=" + status +
                ", currency=" + currency +
                ", sum=" + sum +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}