package com.bank.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AgreementDto {

    @Schema(description = "Agreement id", defaultValue = "1", type = "Long", nullable = true)
    private Long id;

    @Schema(description = "Account iban", defaultValue = "00000000-0000-0000-0000-000000000000")
    private String accountIban;

    @Schema(description = "Product id", defaultValue = "1", type = "Long")
    private Long productId;

    @Schema(description = "Interest rate of product", defaultValue = "111111.1111",
            type = "Double")
    private Double interestRate;

    @Schema(description = "Agreement status", defaultValue = "true", type = "boolean")
    private boolean status;

    @Schema(description = "CurrencyAbbreviation", defaultValue = "USD")
    private String currencyAbb;

    @Schema(description = "Agreement sum", defaultValue = "10.00", type = "BigDecimal")
    private BigDecimal sum;

    @Schema(description = "Account date of creation", type = "LocalDateTime",
            nullable = true)
    private LocalDateTime createdAt;

    @Schema(description = "Account date of last update", type = "LocalDateTime",
            nullable = true)
    private LocalDateTime updatedAt;
}
