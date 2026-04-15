package com.projectC.api.infrastructure.persistence.save.repository;

import com.projectC.api.infrastructure.persistence.save.SaveDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringDataSaveRepository extends MongoRepository<SaveDocument, String> {
    List<SaveDocument> findByUserId(String userId);
    boolean existsByPostIdAndUserId(String postId, String userId);
    void deleteByPostIdAndUserId(String postId, String userId);
    List<SaveDocument> findByUserIdAndPostIdIn(String userId, List<String> postIds);
}
