package com.bank.domain.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private Long id;

    private Long managerId;

    private String name;

    private boolean status;

    private String currencyAbb;

    private double interestRate;

    private Integer limit;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
