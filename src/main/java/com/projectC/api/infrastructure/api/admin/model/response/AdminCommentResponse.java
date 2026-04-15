package com.projectC.api.infrastructure.api.admin.model.response;

import com.projectC.api.domain.model.PostComment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Dados de um comentário retornados para o admin")
public record AdminCommentResponse(
        String id,
        String postId,
        String userCommentUserId,
        String userCommentUsername,
        String userCommentName,
        String commentText,
        LocalDateTime createdAt,
        String responseTo
) {
    public static AdminCommentResponse from(PostComment comment) {
        return new AdminCommentResponse(
                comment.getId(),
                comment.getPostId(),
                comment.getUserCommentUserId(),
                comment.getUserCommentUsername(),
                comment.getUserCommentName(),
                comment.getCommentText(),
                comment.getCreatedAt(),
                comment.getResponseTo()
        );
    }
}

