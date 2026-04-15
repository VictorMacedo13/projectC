package com.projectC.api.infrastructure.api.post.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para criação de um post")
public record CreatePostRequest(

        @Schema(description = "URL da imagem do post", example = "https://cdn.example.com/img.jpg")
        String postImageUrl,

        @NotNull(message = "Rate é obrigatório")
        @DecimalMin(value = "0.0") @DecimalMax(value = "5.0")
        @Schema(description = "Avaliação do local (0 a 5)", example = "4.5")
        Double rate,

        @Schema(description = "Texto curto do post", example = "Lugar incrível!")
        String postText,

        @NotBlank(message = "Título da review é obrigatório")
        @Schema(description = "Título da review", example = "Melhor restaurante da cidade")
        String reviewTitle,

        @NotBlank(message = "Nome do local é obrigatório")
        @Schema(description = "Nome do local avaliado", example = "Restaurante do João")
        String localName,

        @NotNull(message = "Coordenada X é obrigatória")
        @Schema(description = "Coordenada X (longitude)", example = "-46.633309")
        Double coordinateX,

        @NotNull(message = "Coordenada Y é obrigatória")
        @Schema(description = "Coordenada Y (latitude)", example = "-23.550520")
        Double coordinateY,

        @Schema(description = "Coordenada Z (altitude)", example = "760.0")
        Double coordinateZ,

        @Schema(description = "Corpo completo do post", example = "O ambiente é aconchegante e a comida é deliciosa...")
        String postBody
) {
}

