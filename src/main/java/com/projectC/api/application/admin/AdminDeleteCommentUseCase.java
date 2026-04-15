package com.projectC.api.application.admin;

import com.projectC.api.domain.repository.PostCommentRepository;
import com.projectC.api.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class AdminDeleteCommentUseCase {

    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;

    public AdminDeleteCommentUseCase(PostCommentRepository postCommentRepository, PostRepository postRepository) {
        this.postCommentRepository = postCommentRepository;
        this.postRepository = postRepository;
    }

    public void execute(String commentId) {
        var comment = postCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comentário não encontrado"));

        postCommentRepository.deleteById(commentId);

        postRepository.findById(comment.getPostId()).ifPresent(post -> {
            post.setCommentsCount(Math.max(0, post.getCommentsCount() - 1));
            postRepository.save(post);
        });
    }
}

