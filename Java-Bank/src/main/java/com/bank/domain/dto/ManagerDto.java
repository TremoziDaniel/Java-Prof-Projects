package com.bank.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ManagerDto {

    @Schema(description = "Manager id", defaultValue = "1", type = "Long", nullable = true)
    private Long id;

    @Schema(description = "Manager status", defaultValue = "true", type = "boolean")
    private boolean status;

    @Schema(description = "Personal data id", defaultValue = "1", type = "Long")
    private Long personalDataId;

    @Schema(description = "Account date of creation", type = "LocalDateTime",
            nullable = true)
    private LocalDateTime createdAt;

    @Schema(description = "Account date of creation", type = "LocalDateTime",
            nullable = true)
    private LocalDateTime updatedAt;
}
