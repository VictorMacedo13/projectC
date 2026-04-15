package com.projectC.api.infrastructure.api.user.model.response;

import com.projectC.api.domain.model.Follow;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de um seguidor")
public record FollowerResponse(
        String follower,
        String followerUserName,
        String followerName,
        String followerUserPictureUrl
) {
    public static FollowerResponse from(Follow follow) {
        return new FollowerResponse(
                follow.getFollower(),
                follow.getFollowerUserName(),
                follow.getFollowerName(),
                follow.getFollowerUserPictureUrl()
        );
    }
}

