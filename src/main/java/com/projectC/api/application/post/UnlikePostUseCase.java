package com.projectC.api.application.post;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.PostLikeRepository;
import com.projectC.api.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class UnlikePostUseCase {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    public UnlikePostUseCase(PostRepository postRepository, PostLikeRepository postLikeRepository) {
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
    }

    public void execute(User user, String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post não encontrado"));

        if (!postLikeRepository.existsByPostIdAndUserLikeId(postId, user.getId())) {
            throw new IllegalArgumentException("Você ainda não curtiu este post");
        }

        postLikeRepository.deleteByPostIdAndUserLikeId(postId, user.getId());

        post.setLikesCount(Math.max(0, post.getLikesCount() - 1));
        postRepository.save(post);
    }
}

