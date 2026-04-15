package com.projectC.api.infrastructure.api.admin.controller;

import com.projectC.api.infrastructure.api.admin.model.request.AdminBanUserRequest;
import com.projectC.api.infrastructure.api.admin.model.request.AdminCreateUserRequest;
import com.projectC.api.infrastructure.api.admin.model.request.AdminEditUserRequest;
import com.projectC.api.infrastructure.api.admin.model.response.AdminCommentResponse;
import com.projectC.api.infrastructure.api.admin.model.response.AdminUserResponse;
import com.projectC.api.infrastructure.api.common.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Admin", description = "Endpoints exclusivos para administradores")
@SecurityRequirement(name = "bearerAuth")
public interface AdminAPI {

    @Operation(summary = "Cadastrar usuário com roles específicas")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou conflito", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado: requer role ADMIN", content = @Content)
    })
    ResponseEntity<AdminUserResponse> createUser(@RequestBody AdminCreateUserRequest req);

    @Operation(summary = "Listar todos os usuários (paginado)")
    @ApiResponse(responseCode = "200", description = "Lista paginada de usuários")
    ResponseEntity<PageResponse<AdminUserResponse>> listUsers(Pageable pageable);

    @Operation(summary = "Consultar usuário por email ou username")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado", content = @Content)
    })
    ResponseEntity<AdminUserResponse> getUser(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String username
    );

    @Operation(summary = "Editar dados de um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário editado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado: requer role ADMIN", content = @Content)
    })
    ResponseEntity<AdminUserResponse> editUser(@PathVariable String userId, @RequestBody AdminEditUserRequest req);

    @Operation(summary = "Banir usuário temporariamente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário banido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado: requer role ADMIN", content = @Content)
    })
    ResponseEntity<AdminUserResponse> banUser(@PathVariable String userId, @RequestBody AdminBanUserRequest req);

    @Operation(summary = "Desbanir usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário desbanido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado: requer role ADMIN", content = @Content)
    })
    ResponseEntity<AdminUserResponse> unbanUser(@PathVariable String userId);

    @Operation(summary = "Excluir post (admin)")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Post excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Post não encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado: requer role ADMIN", content = @Content)
    })
    ResponseEntity<Void> deletePost(@PathVariable String postId);

    @Operation(summary = "Listar todos os comentários (paginado)")
    @ApiResponse(responseCode = "200", description = "Lista paginada de comentários")
    ResponseEntity<PageResponse<AdminCommentResponse>> listComments(
            @RequestParam(required = false) String postId,
            Pageable pageable
    );

    @Operation(summary = "Consultar comentário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comentário encontrado"),
            @ApiResponse(responseCode = "400", description = "Comentário não encontrado", content = @Content)
    })
    ResponseEntity<AdminCommentResponse> getComment(@PathVariable String commentId);

    @Operation(summary = "Excluir comentário (admin)")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Comentário excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Comentário não encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado: requer role ADMIN", content = @Content)
    })
    ResponseEntity<Void> deleteComment(@PathVariable String commentId);
}

