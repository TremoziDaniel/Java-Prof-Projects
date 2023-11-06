package com.bank.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManagerDto {

    private Long id;

    private boolean status;

    private Long personalDataId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
