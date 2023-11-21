package com.bank.domain.dto;

import com.bank.domain.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDto {

    @Schema(description = "Transaction id", type = "Long", nullable = true)
    private Long id;

    @Schema(description = "Credit account iban", nullable = true)
    private String creditAccountIban;

    @Schema(description = "Debit account iban", nullable = true)
    private String debitAccountIban;

    @Schema(description = "Transaction type", defaultValue = "PERSONAL", type = "TransactionType")
    private TransactionType type;

    @Schema(description = "Currency abbreviation", defaultValue = "USD",
            nullable = true)
    private String currencyAbb;

    @Schema(description = "Amount of transacted money", defaultValue = "1000.00", type = "BigDecimal")
    private BigDecimal amount;

    @Schema(description = "Short transaction description", defaultValue = "Sending money to family")
    private String description;

    @Schema(description = "Date of making transaction", type = "LocalDateTime",
            nullable = true)
    private LocalDateTime completedAt;
}
