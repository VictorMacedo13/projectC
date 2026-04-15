package com.projectC.api.infrastructure.api.user.model.response;

import com.projectC.api.domain.model.Follow;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de um usuário seguido")
public record FollowingResponse(
        String following,
        String followingUserName,
        String followingName,
        String followingUserPictureUrl
) {
    public static FollowingResponse from(Follow follow) {
        return new FollowingResponse(
                follow.getFollowing(),
                follow.getFollowingUserName(),
                follow.getFollowingName(),
                follow.getFollowingUserPictureUrl()
        );
    }
}

