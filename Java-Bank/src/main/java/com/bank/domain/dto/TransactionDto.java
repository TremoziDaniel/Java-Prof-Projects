package com.bank.domain.dto;

import com.bank.domain.enums.TransactionType;
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
public class TransactionDto {

    private Long id;

    private String creditAccountIban;

    private String debitAccountIban;

    private TransactionType type;

    private String currencyAbb;

    private BigDecimal amount;

    private String description;

    private LocalDateTime completedAt;
}
