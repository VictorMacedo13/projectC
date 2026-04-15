package com.projectC.api.infrastructure.api.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Dados de autenticação")
public record AuthRequest(
        @NotBlank(message = "Email é obrigatório")
        @Schema(description = "E-mail do usuário", example = "user@dgnotas.com") String email,

        @NotBlank(message = "Senha é obrigatória")
        @Schema(description = "Senha do usuário", example = "senha123") String password,

        @NotBlank(message = "Nome é obrigatório")
        @Schema(description = "Nome do usuário (apenas para registro)", example = "João Silva") String name,

        @NotBlank(message = "Username é obrigatório")
        @Pattern(regexp = "^\\S+$", message = "Username não pode conter espaços")
        @Schema(description = "Username único do usuário (apenas para registro)", example = "joaosilva") String username
) {
}
