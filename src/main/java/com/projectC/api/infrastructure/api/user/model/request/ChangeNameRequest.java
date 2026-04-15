package com.projectC.api.infrastructure.api.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para alteração de nome")
public record ChangeNameRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Schema(description = "Novo nome do usuário", example = "João Silva") String name
) {
}

