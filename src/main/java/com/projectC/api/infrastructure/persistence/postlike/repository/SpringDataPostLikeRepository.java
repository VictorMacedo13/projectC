package com.projectC.api.infrastructure.persistence.postlike.repository;

import com.projectC.api.infrastructure.persistence.postlike.PostLikeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringDataPostLikeRepository extends MongoRepository<PostLikeDocument, String> {
    boolean existsByPostIdAndUserLikeId(String postId, String userLikeId);
    void deleteByPostIdAndUserLikeId(String postId, String userLikeId);
    List<PostLikeDocument> findByUserLikeIdAndPostIdIn(String userLikeId, List<String> postIds);
}
