package com.projectC.api.application.post;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.model.PostUser;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreatePostUseCase {

    private final PostRepository postRepository;

    public CreatePostUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public record Input(
            String postImageUrl,
            Double rate,
            String postText,
            String reviewTitle,
            String localName,
            Double coordinateX,
            Double coordinateY,
            Double coordinateZ,
            String postBody
    ) {}

    public Post execute(User user, Input input) {
        PostUser postUser = new PostUser();
        postUser.setUserId(user.getId());
        postUser.setName(user.getName());
        postUser.setUsername(user.getUsername());
        postUser.setUserScore(user.getScore());
        postUser.setUserProfilePictureUrl(user.getProfilePictureUrl());

        Post post = new Post();
        post.setUser(postUser);
        post.setPostImageUrl(input.postImageUrl());
        post.setRate(input.rate());
        post.setPostText(input.postText());
        post.setReviewTitle(input.reviewTitle());
        post.setLocalName(input.localName());
        post.setCoordinateX(input.coordinateX());
        post.setCoordinateY(input.coordinateY());
        post.setCoordinateZ(input.coordinateZ());
        post.setPostBody(input.postBody());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }
}
