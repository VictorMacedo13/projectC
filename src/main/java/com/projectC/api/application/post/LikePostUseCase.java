package com.projectC.api.application.post;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.model.PostLike;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.PostLikeRepository;
import com.projectC.api.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class LikePostUseCase {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    public LikePostUseCase(PostRepository postRepository, PostLikeRepository postLikeRepository) {
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
    }

    public PostLike execute(User user, String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post não encontrado"));

        if (postLikeRepository.existsByPostIdAndUserLikeId(postId, user.getId())) {
            throw new IllegalArgumentException("Você já curtiu este post");
        }

        PostLike like = new PostLike();
        like.setPostId(postId);
        like.setUserLikeId(user.getId());
        like.setUserLikeUsername(user.getUsername());
        like.setUserLikePictureUrl(user.getProfilePictureUrl());
        like.setUserLikeName(user.getName());

        post.setLikesCount(post.getLikesCount() + 1);
        postRepository.save(post);

        return postLikeRepository.save(like);
    }
}

