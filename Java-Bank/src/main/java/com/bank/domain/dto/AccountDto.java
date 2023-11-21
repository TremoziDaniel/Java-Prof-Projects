package com.bank.domain.dto;

import com.bank.domain.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDto {
    // @JsonFormat, @JsonIgnore(@JsonBackReference, @JsonManagedReference)
    @Schema(description = "Account id", defaultValue = "00000000-0000-0000-0000-000000000000",
            type = "UUID", nullable = true)
    private UUID id;

    @Schema(description = "Account iban", defaultValue = "BI12A1B212345671234567812345678",
            minLength = 15, maxLength = 35)
    private String iban;

    @Schema(description = "Client id", defaultValue = "00000000-0000-0000-0000-000000000000")
    private String clientId;

    @Schema(description = "Account name", defaultValue = "My account",
            minLength = 3, maxLength = 255)
    private String name;

    @Schema(description = "Account type", defaultValue = "PERSONAL")
    private AccountType type;

    @Schema(description = "Account status", defaultValue = "true", type = "boolean")
    private boolean status;

    @Schema(description = "Currency abbreviation", defaultValue = "USD")
    private String currencyAbb;

    @Schema(description = "Account balance")
    private BigDecimal balance;

    @Schema(description = "Account date of creation", type = "LocalDateTime",
            nullable = true)
    private LocalDateTime createdAt;

    @Schema(description = "Account date of last update", type = "LocalDateTime",
            nullable = true)
    private LocalDateTime updatedAt;
}
