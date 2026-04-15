package com.projectC.api.infrastructure.api.post.model.response;

import com.projectC.api.domain.model.PostUser;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados do usuário autor do post no feed")
public record FeedPostUserResponse(
        String id,
        String name,
        String username,
        String profilePictureUrl,
        Integer score
) {
    public static FeedPostUserResponse from(PostUser user) {
        if (user == null) return null;
        return new FeedPostUserResponse(
                user.getUserId(),
                user.getName(),
                user.getUsername(),
                user.getUserProfilePictureUrl(),
                user.getUserScore()
        );
    }
}
