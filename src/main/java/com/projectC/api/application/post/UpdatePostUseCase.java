package com.projectC.api.application.post;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdatePostUseCase {

    private final PostRepository postRepository;

    public UpdatePostUseCase(PostRepository postRepository) {
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

    public Post execute(User user, String postId, Input input) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post não encontrado"));

        if (!post.getUser().getUserId().equals(user.getId())) {
            throw new IllegalArgumentException("Você não tem permissão para editar este post");
        }

        if (input.postImageUrl() != null) post.setPostImageUrl(input.postImageUrl());
        if (input.rate() != null)         post.setRate(input.rate());
        if (input.postText() != null)     post.setPostText(input.postText());
        if (input.reviewTitle() != null)  post.setReviewTitle(input.reviewTitle());
        if (input.localName() != null)    post.setLocalName(input.localName());
        if (input.coordinateX() != null)  post.setCoordinateX(input.coordinateX());
        if (input.coordinateY() != null)  post.setCoordinateY(input.coordinateY());
        if (input.coordinateZ() != null)  post.setCoordinateZ(input.coordinateZ());
        if (input.postBody() != null)     post.setPostBody(input.postBody());

        post.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }
}

