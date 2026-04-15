package com.projectC.api.infrastructure.api.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para atualização da foto de perfil")
public record ChangeProfilePictureRequest(
        @NotBlank(message = "URL da foto é obrigatória")
        @Schema(description = "URL da foto de perfil", example = "https://cdn.example.com/foto.jpg") String profilePictureUrl
) {
}

