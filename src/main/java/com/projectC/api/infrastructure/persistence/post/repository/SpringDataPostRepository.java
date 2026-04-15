package com.projectC.api.infrastructure.persistence.post.repository;

import com.projectC.api.infrastructure.persistence.post.PostDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataPostRepository extends MongoRepository<PostDocument, String> {
    Page<PostDocument> findByUserUserIdOrderByCreatedAtDesc(String userId, Pageable pageable);
    Page<PostDocument> findByUserUserIdInOrderByCreatedAtDesc(List<String> userIds, Pageable pageable);
    Page<PostDocument> findByUserUserIdInAndCreatedAtAfter(List<String> userIds, LocalDateTime after, Pageable pageable);
}
