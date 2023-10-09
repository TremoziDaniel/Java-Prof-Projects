package com.bank.domain.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

public class ClientDto {

    private UUID id;

    private ManagerDto manager;

    private boolean status;

    private String taxCode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ClientDto() {
    }

    public ClientDto(UUID id, ManagerDto manager, boolean status, String taxCode,
                     LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.manager = manager;
        this.status = status;
        this.taxCode = taxCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
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
        return "ClientDto{" +
                "id=" + id +
                ", manager=" + manager.getId() +
                ", status=" + status +
                ", taxCode='" + taxCode + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}