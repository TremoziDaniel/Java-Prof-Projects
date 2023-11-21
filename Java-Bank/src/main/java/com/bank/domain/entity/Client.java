package com.bank.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    private UUID id;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    @Basic
    private boolean status;

    @Pattern(message = "Invalid tax code.\nExample: ABCDEF12A1B2C3DA",
            regexp = "^[A-Z]{6}[-\\s.]?[0-9]{2}[-\\s.]?[A-Z0-9]{7}[-\\s.]?[A-Z]$")
    private String taxCode;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_data_id", referencedColumnName = "id")
    private PersonalData personalData;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Client(UUID id, Manager manager, boolean status, String taxCode,
                  PersonalData personalData, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
        this.manager = manager;
        this.status = status;
        this.taxCode = taxCode;
        this.personalData = personalData;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", manager=" + manager +
                ", status=" + status +
                ", taxCode='" + taxCode + '\'' +
                ", personalData=" + personalData +
                ", accounts=" + accounts.stream().map(Account::getId).collect(Collectors.toList()) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
