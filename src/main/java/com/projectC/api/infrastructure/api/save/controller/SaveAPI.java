package com.projectC.api.infrastructure.api.save.controller;

import com.projectC.api.domain.model.User;
import com.projectC.api.infrastructure.api.save.model.response.SaveResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Saves", description = "Endpoints para salvar e consultar posts salvos")
public interface SaveAPI {

    @Operation(summary = "Salvar post", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Post salvo com sucesso",
                    content = @Content(schema = @Schema(implementation = SaveResponse.class))),
            @ApiResponse(responseCode = "400", description = "Post não encontrado, já salvo ou é seu próprio post", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    ResponseEntity<SaveResponse> savePost(@AuthenticationPrincipal User user, @PathVariable String postId);

    @Operation(summary = "Dessalvar post", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Post removido dos salvos com sucesso"),
            @ApiResponse(responseCode = "400", description = "Post não encontrado ou não estava salvo", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    ResponseEntity<Void> unsavePost(@AuthenticationPrincipal User user, @PathVariable String postId);

    @Operation(summary = "Consultar posts salvos de um usuário", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de posts salvos"),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    ResponseEntity<List<SaveResponse>> getUserSaves(@PathVariable String userId);
}

