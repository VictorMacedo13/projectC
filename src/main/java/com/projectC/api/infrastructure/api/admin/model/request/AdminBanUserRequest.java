package com.projectC.api.infrastructure.api.admin.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(description = "Dados para banir um usuário temporariamente")
public record AdminBanUserRequest(
        @NotNull(message = "Data de banimento é obrigatória")
        @Future(message = "A data de banimento deve ser futura")
        @Schema(description = "Data/hora até quando o usuário ficará banido", example = "2026-05-01T00:00:00")
        LocalDateTime bannedUntil
) {}

