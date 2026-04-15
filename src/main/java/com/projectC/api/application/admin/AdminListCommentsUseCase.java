package com.projectC.api.application.admin;

import com.projectC.api.domain.model.PostComment;
import com.projectC.api.domain.repository.PostCommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class AdminListCommentsUseCase {

    private final PostCommentRepository postCommentRepository;

    public AdminListCommentsUseCase(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    public Page<PostComment> execute(Pageable pageable) {
        return postCommentRepository.findAll(pageable);
    }

    public Page<PostComment> executeByPostId(String postId, Pageable pageable) {
        return postCommentRepository.findByPostId(postId, pageable);
    }
}

