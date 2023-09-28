package com.bank.domain.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ManagerDto {

    private long id;

    private PersonalDataDto personalData;

    private boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ManagerDto() {
    }

    public ManagerDto(long id, PersonalDataDto personalData, boolean status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.personalData = personalData;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonalDataDto getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalDataDto personalData) {
        this.personalData = personalData;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
        return "ManagerDto{" +
                "id=" + id +
                ", personalData=" + personalData +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}