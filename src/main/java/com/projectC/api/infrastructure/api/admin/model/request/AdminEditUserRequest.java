package com.projectC.api.infrastructure.api.admin.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Schema(description = "Dados para edição de usuário pelo admin")
public record AdminEditUserRequest(
        @Schema(example = "Novo Nome") String name,

        @Pattern(regexp = "^\\S*$", message = "Username não pode conter espaços")
        @Schema(example = "novousername") String username,

        @Schema(example = "https://cdn.example.com/pic.jpg") String profilePictureUrl,

        @Schema(example = "[\"USER\", \"MODERATOR\"]") List<String> roles
) {}

