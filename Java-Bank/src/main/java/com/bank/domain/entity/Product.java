package com.bank.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    private String name;

    private boolean status;

    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    private Double interestRate;

    @Column(name="product_limit")
    private int limit;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Product() {
    }

    public Product(long id, Manager manager, String name, boolean status, Currency currency,
                   Double interestRate, int limit, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
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

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
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
        return "Product{" +
                "id=" + id +
                ", manager=" + manager +
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