package com.projectC.api.infrastructure.api.admin.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Schema(description = "Dados para criação de usuário pelo admin")
public record AdminCreateUserRequest(
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        @Schema(example = "admin@example.com") String email,

        @NotBlank(message = "Senha é obrigatória")
        @Schema(example = "senha123") String password,

        @NotBlank(message = "Nome é obrigatório")
        @Schema(example = "João Silva") String name,

        @NotBlank(message = "Username é obrigatório")
        @Pattern(regexp = "^\\S+$", message = "Username não pode conter espaços")
        @Schema(example = "joaosilva") String username,

        @NotEmpty(message = "Roles são obrigatórias")
        @Schema(example = "[\"USER\", \"ADMIN\"]") List<String> roles
) {}

