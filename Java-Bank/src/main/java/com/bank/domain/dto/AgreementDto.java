package com.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgreementDto {

    private Long id;

    private String accountIban;

    private Long productId;

    private Double interestRate;

    private boolean status;

    private String currencyAbb;

    private BigDecimal sum;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
