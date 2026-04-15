package com.projectC.api.infrastructure.api.admin.model.response;

import com.projectC.api.domain.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Dados de um usuário retornados para o admin")
public record AdminUserResponse(
        String id,
        String email,
        String name,
        String username,
        String profilePictureUrl,
        Long followersCount,
        Long followingCount,
        Integer score,
        Long postsCount,
        List<String> roles,
        LocalDateTime bannedUntil
) {
    public static AdminUserResponse from(User user) {
        return new AdminUserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getUsername(),
                user.getProfilePictureUrl(),
                user.getFollowersCount(),
                user.getFollowingCount(),
                user.getScore(),
                user.getPostsCount(),
                user.getRoles(),
                user.getBannedUntil()
        );
    }
}

