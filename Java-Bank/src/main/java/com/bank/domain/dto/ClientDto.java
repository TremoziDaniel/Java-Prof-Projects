package com.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {

    private UUID id;

    private Long managerId;

    private boolean status;

    private String taxCode;

    private Long personalDataId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
