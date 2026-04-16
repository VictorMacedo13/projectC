package com.projectC.api.infrastructure.api.user.model.response;

import com.projectC.api.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;

public record UserMeResponse(
        String id,
        String name,
        String username,
        String email,
        String profilePictureUrl,
        Integer score,
        Long followersCount,
        Long followingCount,
        Long postsCount,
        Long savedPoints,
        List<String> roles,
        LocalDateTime bannedUntil
) {
    public static UserMeResponse from(User user) {
        return new UserMeResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getProfilePictureUrl(),
                user.getScore(),
                user.getFollowersCount(),
                user.getFollowingCount(),
                user.getPostsCount(),
                user.getSavedPoints(),
                user.getRoles(),
                user.getBannedUntil()
        );
    }
}

