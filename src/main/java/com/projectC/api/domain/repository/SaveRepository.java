package com.projectC.api.domain.repository;

import com.projectC.api.domain.model.Save;

import java.util.List;
import java.util.Set;

public interface SaveRepository {
    Save save(Save save);
    List<Save> findByUserId(String userId);
    boolean existsByPostIdAndUserId(String postId, String userId);
    void deleteByPostIdAndUserId(String postId, String userId);
    Set<String> findSavedPostIdsByUser(String userId, List<String> postIds);
}
