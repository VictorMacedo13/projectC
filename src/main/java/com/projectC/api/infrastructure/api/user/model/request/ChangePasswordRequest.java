package com.projectC.api.infrastructure.api.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para alteração de senha")
public record ChangePasswordRequest(
        @NotBlank(message = "Senha atual é obrigatória")
        @Schema(description = "Senha atual", example = "senha123") String currentPassword,

        @NotBlank(message = "Nova senha é obrigatória")
        @Schema(description = "Nova senha", example = "novaSenha456") String newPassword
) {
}

