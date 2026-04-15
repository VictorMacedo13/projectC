package com.projectC.api.infrastructure.api.report.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados do report")
public record ReportRequest(
        @NotBlank(message = "Categoria do report é obrigatória")
        @Schema(example = "SPAM") String reportCategory,

        @NotBlank(message = "Mensagem do report é obrigatória")
        @Schema(example = "Este conteúdo é inapropriado") String reportMessage
) {}

