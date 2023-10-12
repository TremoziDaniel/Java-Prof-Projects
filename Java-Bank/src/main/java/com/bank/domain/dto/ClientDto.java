package com.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {

    private UUID id;

    private String managerName;

    private boolean status;

    private String taxCode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ClientDto() {
    }

    public ClientDto(UUID id, String managerName, boolean status, String taxCode, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.managerName = managerName;
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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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
                ", managerName='" + managerName + '\'' +
                ", status=" + status +
                ", taxCode='" + taxCode + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}