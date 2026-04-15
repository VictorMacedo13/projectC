package com.projectC.api.infrastructure.api.post.model.response;

import com.projectC.api.domain.model.PostUser;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados do usuário associado ao post")
public record PostUserResponse(
        String userId,
        String username,
        Integer userScore,
        String userProfilePictureUrl
) {
    public static PostUserResponse from(PostUser user) {
        if (user == null) return null;
        return new PostUserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getUserScore(),
                user.getUserProfilePictureUrl()
        );
    }
}

