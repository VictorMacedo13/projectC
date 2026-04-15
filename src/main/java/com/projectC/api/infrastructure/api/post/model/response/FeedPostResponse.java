package com.projectC.api.infrastructure.api.post.model.response;

import com.projectC.api.application.post.FeedPost;
import com.projectC.api.domain.model.Post;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Post completo para o feed")
public record FeedPostResponse(
        String id,
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
        Long savesCount,
        boolean likedByMe,
        boolean savedByMe,
        FeedPostUserResponse userPostData
) {
    public static FeedPostResponse from(FeedPost feedPost) {
        Post post = feedPost.post();
        return new FeedPostResponse(
                post.getId(),
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
                post.getSavesCount(),
                feedPost.likedByMe(),
                feedPost.savedByMe(),
                FeedPostUserResponse.from(post.getUser())
        );
    }
}
