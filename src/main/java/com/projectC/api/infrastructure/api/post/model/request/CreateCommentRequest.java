package com.projectC.api.infrastructure.api.post.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para criação de um comentário")
public record CreateCommentRequest(

        @NotBlank(message = "Texto do comentário é obrigatório")
        @Schema(description = "Texto do comentário", example = "Que lugar incrível!")
        String commentText,

        @Schema(description = "ID do comentário ao qual está respondendo (nulo se não for resposta)", example = "abc123")
        String responseTo
) {
}

