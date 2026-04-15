package com.projectC.api.infrastructure.api.post.model.response;

import com.projectC.api.domain.model.PostComment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Comentário com respostas aninhadas")
public record PostCommentTreeResponse(
        String id,
        String postId,
        String userCommentUserId,
        String userCommentUsername,
        String userCommentPictureUrl,
        String userCommentName,
        String commentText,
        LocalDateTime createdAt,
        List<PostCommentTreeResponse> responseTo
) {
    public static PostCommentTreeResponse from(PostComment comment, List<PostCommentTreeResponse> replies) {
        return new PostCommentTreeResponse(
                comment.getId(),
                comment.getPostId(),
                comment.getUserCommentUserId(),
                comment.getUserCommentUsername(),
                comment.getUserCommentPictureUrl(),
                comment.getUserCommentName(),
                comment.getCommentText(),
                comment.getCreatedAt(),
                replies
        );
    }
}

