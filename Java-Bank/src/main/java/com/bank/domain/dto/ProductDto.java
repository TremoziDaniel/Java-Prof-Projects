package com.bank.domain.dto;

import java.time.LocalDateTime;
import com.bank.domain.entity.Currency;

public class ProductDto {

    private long id;

    private ManagerDto manager;

    private String name;

    private boolean status;

    private Currency currency;

    private double interestRate;

    private int limit;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ProductDto() {
    }

    public ProductDto(long id, ManagerDto manager, String name, boolean status, Currency currency,
                      double interestRate, int limit, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.manager = manager;
        this.name = name;
        this.status = status;
        this.currency = currency;
        this.interestRate = interestRate;
        this.limit = limit;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
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

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
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
        return "ProductDto{" +
                "id=" + id +
                ", manager=" + manager.getId() +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", currency=" + currency +
                ", interestRate=" + interestRate +
                ", limit=" + limit +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}