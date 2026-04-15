package com.projectC.api.infrastructure.api.post.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

@Schema(description = "Dados para atualização de um post")
public record UpdatePostRequest(

        @Schema(description = "URL da imagem do post", example = "https://cdn.example.com/img.jpg")
        String postImageUrl,

        @DecimalMin(value = "0.0") @DecimalMax(value = "5.0")
        @Schema(description = "Avaliação do local (0 a 5)", example = "4.5")
        Double rate,

        @Schema(description = "Texto curto do post", example = "Lugar incrível!")
        String postText,

        @Schema(description = "Título da review", example = "Melhor restaurante da cidade")
        String reviewTitle,

        @Schema(description = "Nome do local avaliado", example = "Restaurante do João")
        String localName,

        @Schema(description = "Coordenada X (longitude)", example = "-46.633309")
        Double coordinateX,

        @Schema(description = "Coordenada Y (latitude)", example = "-23.550520")
        Double coordinateY,

        @Schema(description = "Coordenada Z (altitude)", example = "760.0")
        Double coordinateZ,

        @Schema(description = "Corpo completo do post", example = "O ambiente é aconchegante...")
        String postBody
) {
}

