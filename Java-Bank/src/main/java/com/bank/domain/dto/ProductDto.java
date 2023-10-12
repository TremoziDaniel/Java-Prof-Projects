package com.bank.domain.dto;

import java.time.LocalDateTime;
import com.bank.domain.entity.Currency;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private long id;

    private String managerName;

    private String name;

    private boolean status;

    private String currencyAbb;

    private double interestRate;

    private int limit;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ProductDto() {
    }

    public ProductDto(long id, String managerName, String name, boolean status, String currencyAbb,
                      double interestRate, int limit, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.managerName = managerName;
        this.name = name;
        this.status = status;
        this.currencyAbb = currencyAbb;
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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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

    public String getCurrencyAbb() {
        return currencyAbb;
    }

    public void setCurrencyAbb(String currencyAbb) {
        this.currencyAbb = currencyAbb;
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
                ", managerName='" + managerName + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", currencyAbb='" + currencyAbb + '\'' +
                ", interestRate=" + interestRate +
                ", limit=" + limit +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}