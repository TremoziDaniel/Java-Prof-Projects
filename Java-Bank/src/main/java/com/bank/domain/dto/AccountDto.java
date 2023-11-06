package com.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {

    @Getter
    private UUID id;

    private String iban;

    private String clientId;

    private String name;

    private boolean status;

    private String currency;

    private BigDecimal balance;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
