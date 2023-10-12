package com.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {

    // ask about logic in dto(passwords, null fields...)
    // @JsonProperty
    private UUID id;

    private String clientName;

    private String name;

    private boolean status;

    private String currencyAbb;

    private BigDecimal balance;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public AccountDto() {
    }

    public AccountDto(UUID id, String clientName, String name, boolean status, String currencyAbb,
                      BigDecimal balance, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.clientName = clientName;
        this.name = name;
        this.status = status;
        this.currencyAbb = currencyAbb;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AccountDto(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCurrency() {
        return currencyAbb;
    }

    public void setCurrency(String currencyAbb) {
        this.currencyAbb = currencyAbb;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
        return "AccountDto{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", currency='" + currencyAbb + '\'' +
                ", balance=" + balance +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}