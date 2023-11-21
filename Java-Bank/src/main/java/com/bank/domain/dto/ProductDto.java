package com.bank.domain.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

    @Schema(description = "Product id", defaultValue = "1", type = "Long", nullable = true)
    private Long id;

    @Schema(description = "Manager id", defaultValue = "1", type = "Long")
    private Long managerId;

    @Schema(description = "Product name", defaultValue = "My product")
    private String name;

    @Schema(description = "Product status", defaultValue = "true", type = "boolean")
    private boolean status;

    @Schema(description = "Currency abbreviation", defaultValue = "USD")
    private String currency;

    @Schema(description = "Interest rate of product", defaultValue = "111111.1111",
            type = "Double")
    private double interestRate;

    @Schema(description = "Product limit", defaultValue = "10", type = "Integer")
    private Integer limit;

    @Schema(description = "Account date of creation", type = "LocalDateTime",
            nullable = true)
    private LocalDateTime createdAt;

    @Schema(description = "Account date of last update", type = "LocalDateTime",
            nullable = true)
    private LocalDateTime updatedAt;
}
