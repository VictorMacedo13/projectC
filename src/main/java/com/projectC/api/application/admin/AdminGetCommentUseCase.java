package com.projectC.api.application.admin;

import com.projectC.api.domain.model.PostComment;
import com.projectC.api.domain.repository.PostCommentRepository;
import org.springframework.stereotype.Component;

@Component
public class AdminGetCommentUseCase {

    private final PostCommentRepository postCommentRepository;

    public AdminGetCommentUseCase(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    public PostComment execute(String commentId) {
        return postCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comentário não encontrado"));
    }
}

