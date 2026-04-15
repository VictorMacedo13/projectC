package com.projectC.api.application.post;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class DeletePostUseCase {

    private final PostRepository postRepository;

    public DeletePostUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void execute(User user, String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post não encontrado"));

        if (!post.getUser().getUserId().equals(user.getId())) {
            throw new IllegalArgumentException("Você não tem permissão para excluir este post");
        }

        postRepository.deleteById(postId);
    }
}

