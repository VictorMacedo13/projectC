package com.projectC.api.domain.repository;

import com.projectC.api.domain.model.PostLike;

import java.util.List;
import java.util.Set;

public interface PostLikeRepository {
    PostLike save(PostLike postLike);
    boolean existsByPostIdAndUserLikeId(String postId, String userLikeId);
    void deleteByPostIdAndUserLikeId(String postId, String userLikeId);
    Set<String> findLikedPostIdsByUser(String userId, List<String> postIds);
}
