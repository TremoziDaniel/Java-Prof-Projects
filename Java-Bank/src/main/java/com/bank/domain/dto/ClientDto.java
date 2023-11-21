package com.bank.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDto {

    @Schema(description = "Client id", defaultValue = "00000000-0000-0000-0000-000000000000",
            type = "UUID", nullable = true)
    private UUID id;

    @Schema(description = "Manager id", defaultValue = "1", type = "Long")
    private Long managerId;

    @Schema(description = "Client status", defaultValue = "true", type = "boolean")
    private boolean status;

    @Schema(description = "Client tax code", defaultValue = "ABCDEF12A1B2C3DA",
            minLength = 16, maxLength = 16)
    private String taxCode;

    @Schema(description = "Personal data id", defaultValue = "1", type = "Long")
    private Long personalDataId;

    @Schema(description = "Account date of creation", type = "LocalDateTime",
            nullable = true)
    private LocalDateTime createdAt;

    @Schema(description = "Account date of last update", type = "LocalDateTime",
            nullable = true)
    private LocalDateTime updatedAt;
}
