package com.projectC.api.application.admin;

import com.projectC.api.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class AdminDeletePostUseCase {

    private final PostRepository postRepository;

    public AdminDeletePostUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void execute(String postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post não encontrado"));
        postRepository.deleteById(postId);
    }
}

