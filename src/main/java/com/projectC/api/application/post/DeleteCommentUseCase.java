package com.projectC.api.application.post;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.PostCommentRepository;
import com.projectC.api.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteCommentUseCase {

    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;

    public DeleteCommentUseCase(PostCommentRepository postCommentRepository,
                                PostRepository postRepository) {
        this.postCommentRepository = postCommentRepository;
        this.postRepository = postRepository;
    }

    public void execute(User user, String commentId) {
        var comment = postCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comentário não encontrado"));

        if (!comment.getUserCommentUserId().equals(user.getId())) {
            throw new IllegalArgumentException("Você não tem permissão para excluir este comentário");
        }

        postCommentRepository.deleteById(commentId);

        postRepository.findById(comment.getPostId()).ifPresent(post -> {
            post.setCommentsCount(Math.max(0, post.getCommentsCount() - 1));
            postRepository.save(post);
        });
    }
}

