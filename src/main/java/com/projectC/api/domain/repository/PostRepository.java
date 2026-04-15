package com.projectC.api.domain.repository;

import com.projectC.api.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(String id);
    void deleteById(String id);
    Page<Post> findByUserIdOrderByCreatedAtDesc(String userId, Pageable pageable);
    Page<Post> findByUserIdInOrderByCreatedAtDesc(List<String> userIds, Pageable pageable);
    Page<Post> findByUserIdInAndCreatedAtAfter(List<String> userIds, LocalDateTime after, Pageable pageable);
}

