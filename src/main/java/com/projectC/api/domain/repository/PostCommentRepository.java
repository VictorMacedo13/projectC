package com.projectC.api.domain.repository;

import com.projectC.api.domain.model.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostCommentRepository {
    PostComment save(PostComment comment);
    List<PostComment> findByPostId(String postId);
    Optional<PostComment> findById(String id);
    void deleteById(String id);
    Page<PostComment> findAll(Pageable pageable);
    Page<PostComment> findByPostId(String postId, Pageable pageable);
    Page<PostComment> findRootCommentsByPostId(String postId, Pageable pageable);
    List<PostComment> findRepliesByPostIdAndParentIds(String postId, List<String> parentIds);
}
