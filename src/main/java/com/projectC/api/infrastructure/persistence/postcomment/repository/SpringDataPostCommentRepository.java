package com.projectC.api.infrastructure.persistence.postcomment.repository;

import com.projectC.api.infrastructure.persistence.postcomment.PostCommentDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringDataPostCommentRepository extends MongoRepository<PostCommentDocument, String> {
    List<PostCommentDocument> findByPostId(String postId);
    Page<PostCommentDocument> findByPostId(String postId, Pageable pageable);
    Page<PostCommentDocument> findByPostIdAndResponseToIsNull(String postId, Pageable pageable);
    List<PostCommentDocument> findByPostIdAndResponseToIn(String postId, List<String> parentIds);
}
