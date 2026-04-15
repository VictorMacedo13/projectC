package com.projectC.api.infrastructure.api.post.controller;

import com.projectC.api.domain.model.User;
import com.projectC.api.infrastructure.api.common.PageResponse;
import com.projectC.api.infrastructure.api.post.model.request.CreateCommentRequest;
import com.projectC.api.infrastructure.api.post.model.request.CreatePostRequest;
import com.projectC.api.infrastructure.api.post.model.request.UpdatePostRequest;
import com.projectC.api.infrastructure.api.post.model.response.FeedPostResponse;
import com.projectC.api.infrastructure.api.post.model.response.PostCommentResponse;
import com.projectC.api.infrastructure.api.post.model.response.PostCommentTreeResponse;
import com.projectC.api.infrastructure.api.post.model.response.PostLikeResponse;
import com.projectC.api.infrastructure.api.post.model.response.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Posts", description = "Endpoints para criação e gerenciamento de posts")
public interface PostAPI {

    @Operation(summary = "Criar post", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Post criado com sucesso",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content)
    })
    ResponseEntity<PostResponse> createPost(@AuthenticationPrincipal User user, @RequestBody CreatePostRequest req);

    @Operation(summary = "Atualizar post", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Post atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou sem permissão", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content)
    })
    ResponseEntity<PostResponse> updatePost(@AuthenticationPrincipal User user, @PathVariable String postId, @RequestBody UpdatePostRequest req);

    @Operation(summary = "Excluir post", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Post excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Post não encontrado ou sem permissão", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content)
    })
    ResponseEntity<Void> deletePost(@AuthenticationPrincipal User user, @PathVariable String postId);

    @Operation(summary = "Curtir post", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Like registrado com sucesso",
                    content = @Content(schema = @Schema(implementation = PostLikeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Post não encontrado ou já curtido", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content)
    })
    ResponseEntity<PostLikeResponse> likePost(@AuthenticationPrincipal User user, @PathVariable String postId);

    @Operation(summary = "Remover curtida do post", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Like removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Post não encontrado ou like inexistente", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content)
    })
    ResponseEntity<Void> unlikePost(@AuthenticationPrincipal User user, @PathVariable String postId);

    @Operation(summary = "Comentar post", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Comentário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = PostCommentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou post não encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content)
    })
    ResponseEntity<PostCommentResponse> commentPost(@AuthenticationPrincipal User user, @PathVariable String postId, @RequestBody CreateCommentRequest req);

    @Operation(summary = "Listar comentários do post (paginado)", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comentários retornados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Post não encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content)
    })
    ResponseEntity<PageResponse<PostCommentTreeResponse>> getPostComments(@PathVariable String postId, Pageable pageable);

    @Operation(summary = "Excluir próprio comentário", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Comentário excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Comentário não encontrado ou sem permissão", content = @Content),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content)
    })
    ResponseEntity<Void> deleteComment(@AuthenticationPrincipal User user,
                                       @PathVariable String postId,
                                       @PathVariable String commentId);

    @Operation(summary = "Listar posts de um usuário (paginado)", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Posts retornados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content)
    })
    ResponseEntity<PageResponse<FeedPostResponse>> getUserPosts(@AuthenticationPrincipal User user,
                                                                @PathVariable String userId,
                                                                Pageable pageable);
}

