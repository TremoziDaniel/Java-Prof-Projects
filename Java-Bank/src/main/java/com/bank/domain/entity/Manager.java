package com.bank.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "managers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_data_id", referencedColumnName = "id")
    private PersonalData personalData;

    @Basic
    private boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
