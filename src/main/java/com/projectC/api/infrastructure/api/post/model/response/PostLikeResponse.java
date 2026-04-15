package com.projectC.api.infrastructure.api.post.model.response;

import com.projectC.api.domain.model.PostLike;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados do like no post")
public record PostLikeResponse(
        String id,
        String postId,
        String userLikeId,
        String userLikeUsername,
        String userLikePictureUrl,
        String userLikeName
) {
    public static PostLikeResponse from(PostLike like) {
        return new PostLikeResponse(
                like.getId(),
                like.getPostId(),
                like.getUserLikeId(),
                like.getUserLikeUsername(),
                like.getUserLikePictureUrl(),
                like.getUserLikeName()
        );
    }
}

