package com.projectC.api.infrastructure.api.post.model.response;

import com.projectC.api.domain.model.PostComment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Dados do comentário")
public record PostCommentResponse(
        String id,
        String postId,
        String userCommentUserId,
        String userCommentUsername,
        String userCommentPictureUrl,
        String userCommentName,
        String commentText,
        LocalDateTime createdAt,
        String responseTo
) {
    public static PostCommentResponse from(PostComment comment) {
        return new PostCommentResponse(
                comment.getId(),
                comment.getPostId(),
                comment.getUserCommentUserId(),
                comment.getUserCommentUsername(),
                comment.getUserCommentPictureUrl(),
                comment.getUserCommentName(),
                comment.getCommentText(),
                comment.getCreatedAt(),
                comment.getResponseTo()
        );
    }
}

