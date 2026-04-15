package com.projectC.api.application.post;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.model.PostComment;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.PostCommentRepository;
import com.projectC.api.domain.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentPostUseCase {

    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;

    public CommentPostUseCase(PostRepository postRepository, PostCommentRepository postCommentRepository) {
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
    }

    public record Input(String commentText, String responseTo) {}

    public PostComment execute(User user, String postId, Input input) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post não encontrado"));

        PostComment comment = new PostComment();
        comment.setPostId(postId);
        comment.setUserCommentUserId(user.getId());
        comment.setUserCommentUsername(user.getUsername());
        comment.setUserCommentPictureUrl(user.getProfilePictureUrl());
        comment.setUserCommentName(user.getName());
        comment.setCommentText(input.commentText());
        comment.setResponseTo(input.responseTo());
        comment.setCreatedAt(LocalDateTime.now());

        post.setCommentsCount(post.getCommentsCount() + 1);
        postRepository.save(post);

        return postCommentRepository.save(comment);
    }
}

