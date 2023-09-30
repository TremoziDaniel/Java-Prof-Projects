package com.bank.domain.dto;

import com.bank.domain.entity.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.UUID;

public class AccountDto {

    // ask about logic in dto(passwords, null fields...), @JsonProperty
    private UUID id;

    private ClientDto client;

    private String name;

    private boolean status;

    private Currency currency;

    private BigDecimal balance;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public AccountDto() {
    }

    public AccountDto(UUID id, ClientDto client, String name, boolean status, Currency currency,
                      BigDecimal balance, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.client = client;
        this.name = name;
        this.status = status;
        this.currency = currency;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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
                ", client=" + client +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", currency=" + currency +
                ", balance=" + balance +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}