package com.projectC.api.infrastructure.api.post.controller;

import com.projectC.api.domain.model.User;
import com.projectC.api.infrastructure.api.common.PageResponse;
import com.projectC.api.infrastructure.api.post.model.response.FeedPostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Tag(name = "Feed", description = "Endpoints de feed baseado nos usuários seguidos")
public interface FeedAPI {

    @Operation(summary = "Feed paginado dos posts de quem você segue (mais recentes primeiro)",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Feed retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content)
    })
    ResponseEntity<PageResponse<FeedPostResponse>> getFollowingFeed(
            @AuthenticationPrincipal User user,
            Pageable pageable);

    @Operation(summary = "Posts das últimas 24h de quem você segue",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Feed 24h retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido", content = @Content)
    })
    ResponseEntity<PageResponse<FeedPostResponse>> getFollowing24hFeed(
            @AuthenticationPrincipal User user,
            int page,
            int size);
}
