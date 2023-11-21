package com.bank.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrencyDto {

    @Schema(description = "Currency id", defaultValue = "1", type = "Integer", nullable = true)
    private Integer id;

    @Schema(description = "Currency name", defaultValue = "United States dollar")
    private String currencyName;

    @Schema(description = "Currency abbreviation", defaultValue = "USD")
    private String currencyAbb;

    @Schema(description = "Currency rate to dollar", defaultValue = "1.00", type = "BigDecimal")
    private BigDecimal rate;
}
