package com.projectC.api.infrastructure.api.report.controller;

import com.projectC.api.domain.model.User;
import com.projectC.api.infrastructure.api.common.PageResponse;
import com.projectC.api.infrastructure.api.report.model.request.ReportRequest;
import com.projectC.api.infrastructure.api.report.model.response.ReportResponse;
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

@Tag(name = "Reports", description = "Endpoints para reports de posts, comentários e usuários")
public interface ReportAPI {

    @Operation(summary = "Reportar post", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Report criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    ResponseEntity<ReportResponse> reportPost(@AuthenticationPrincipal User user,
                                              @PathVariable String postId,
                                              @RequestBody ReportRequest req);

    @Operation(summary = "Reportar comentário", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Report criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    ResponseEntity<ReportResponse> reportComment(@AuthenticationPrincipal User user,
                                                 @PathVariable String commentId,
                                                 @RequestBody ReportRequest req);

    @Operation(summary = "Reportar usuário", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Report criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content)
    })
    ResponseEntity<ReportResponse> reportUser(@AuthenticationPrincipal User user,
                                              @PathVariable String targetUserId,
                                              @RequestBody ReportRequest req);

    @Operation(summary = "Listar reports de posts (ADMIN)", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista paginada de reports de posts")
    ResponseEntity<PageResponse<ReportResponse>> getPostReports(Pageable pageable);

    @Operation(summary = "Listar reports de comentários (ADMIN)", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista paginada de reports de comentários")
    ResponseEntity<PageResponse<ReportResponse>> getCommentReports(Pageable pageable);

    @Operation(summary = "Listar reports de usuários (ADMIN)", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Lista paginada de reports de usuários")
    ResponseEntity<PageResponse<ReportResponse>> getUserReports(Pageable pageable);
}

