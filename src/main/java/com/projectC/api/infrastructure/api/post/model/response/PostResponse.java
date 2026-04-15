package com.projectC.api.infrastructure.api.post.model.response;

import com.projectC.api.domain.model.Post;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Dados do post")
public record PostResponse(
        String id,
        PostUserResponse user,
        String postImageUrl,
        Double rate,
        String postText,
        String reviewTitle,
        String localName,
        Double coordinateX,
        Double coordinateY,
        Double coordinateZ,
        String postBody,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long likesCount,
        Long commentsCount,
        Long savesCount
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                PostUserResponse.from(post.getUser()),
                post.getPostImageUrl(),
                post.getRate(),
                post.getPostText(),
                post.getReviewTitle(),
                post.getLocalName(),
                post.getCoordinateX(),
                post.getCoordinateY(),
                post.getCoordinateZ(),
                post.getPostBody(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getLikesCount(),
                post.getCommentsCount(),
                post.getSavesCount()
        );
    }
}
