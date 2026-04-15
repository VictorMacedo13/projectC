package com.projectC.api.application.save;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.PostRepository;
import com.projectC.api.domain.repository.SaveRepository;
import org.springframework.stereotype.Component;

@Component
public class UnsavePostUseCase {

    private final PostRepository postRepository;
    private final SaveRepository saveRepository;

    public UnsavePostUseCase(PostRepository postRepository, SaveRepository saveRepository) {
        this.postRepository = postRepository;
        this.saveRepository = saveRepository;
    }

    public void execute(User user, String postId) {
        if (!saveRepository.existsByPostIdAndUserId(postId, user.getId())) {
            throw new IllegalArgumentException("Post não está salvo");
        }

        saveRepository.deleteByPostIdAndUserId(postId, user.getId());

        postRepository.findById(postId).ifPresent(post -> {
            post.setSavesCount(Math.max(0, post.getSavesCount() - 1));
            postRepository.save(post);
        });
    }
}

