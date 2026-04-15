package com.projectC.api.infrastructure.api.user.controller;

import com.projectC.api.domain.model.User;
import com.projectC.api.infrastructure.api.user.model.request.ChangeNameRequest;
import com.projectC.api.infrastructure.api.user.model.request.ChangePasswordRequest;
import com.projectC.api.infrastructure.api.user.model.request.ChangeProfilePictureRequest;
import com.projectC.api.infrastructure.api.common.PageResponse;
import com.projectC.api.infrastructure.api.user.model.response.FollowerResponse;
import com.projectC.api.infrastructure.api.user.model.response.FollowingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Usuários", description = "Endpoints de gerenciamento de usuário")
public interface UserAPI {

    @Operation(summary = "Alterar senha", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Senha atual incorreta ou dados inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    ResponseEntity<Void> changePassword(@AuthenticationPrincipal User user, @RequestBody ChangePasswordRequest req);

    @Operation(summary = "Alterar nome", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Nome alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    ResponseEntity<Void> changeName(@AuthenticationPrincipal User user, @RequestBody ChangeNameRequest req);

    @Operation(summary = "Atualizar foto de perfil", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Foto atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "URL inválida", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    ResponseEntity<Void> changeProfilePicture(@AuthenticationPrincipal User user, @RequestBody ChangeProfilePictureRequest req);

    @Operation(summary = "Seguir usuário", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário seguido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Já segue ou tentativa inválida", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    ResponseEntity<Void> followUser(@AuthenticationPrincipal User user, @PathVariable String userId);

    @Operation(summary = "Deixar de seguir usuário", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deixou de seguir com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não segue este usuário ou tentativa inválida", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    ResponseEntity<Void> unfollowUser(@AuthenticationPrincipal User user, @PathVariable String userId);

    @Operation(summary = "Total de seguidores do usuário logado", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Quantidade de seguidores")
    ResponseEntity<Long> getFollowersCount(@AuthenticationPrincipal User user);

    @Operation(summary = "Total de seguindo do usuário logado", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Quantidade de usuários seguidos")
    ResponseEntity<Long> getFollowingCount(@AuthenticationPrincipal User user);

    @Operation(summary = "Lista de seguidores do usuário logado (paginada)", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de seguidores")
    ResponseEntity<PageResponse<FollowerResponse>> getFollowers(@AuthenticationPrincipal User user, Pageable pageable);

    @Operation(summary = "Lista de usuários que o usuário logado segue (paginada)", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista de seguindo")
    ResponseEntity<PageResponse<FollowingResponse>> getFollowing(@AuthenticationPrincipal User user, Pageable pageable);

    @Operation(summary = "Deletar usuário (ADMIN)", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado: requer role ADMIN", content = @Content)
    })
    ResponseEntity<Void> deleteUser(@PathVariable String userId);
}

