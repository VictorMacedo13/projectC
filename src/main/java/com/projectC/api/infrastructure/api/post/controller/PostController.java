package com.projectC.api.infrastructure.api.post.controller;

import com.projectC.api.application.post.*;
import com.projectC.api.domain.model.User;
import com.projectC.api.infrastructure.api.common.PageResponse;
import com.projectC.api.infrastructure.api.post.model.request.CreateCommentRequest;
import com.projectC.api.infrastructure.api.post.model.request.CreatePostRequest;
import com.projectC.api.infrastructure.api.post.model.request.UpdatePostRequest;
import com.projectC.api.infrastructure.api.post.model.response.FeedPostResponse;
import com.projectC.api.infrastructure.api.post.model.response.PostCommentResponse;
import com.projectC.api.infrastructure.api.post.model.response.PostCommentTreeResponse;
import com.projectC.api.infrastructure.api.post.model.response.PostLikeResponse;
import com.projectC.api.infrastructure.api.post.model.response.PostResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController implements PostAPI {

    private final CreatePostUseCase createPostUseCase;
    private final UpdatePostUseCase updatePostUseCase;
    private final DeletePostUseCase deletePostUseCase;
    private final LikePostUseCase likePostUseCase;
    private final UnlikePostUseCase unlikePostUseCase;
    private final CommentPostUseCase commentPostUseCase;
    private final GetPostCommentsUseCase getPostCommentsUseCase;
    private final DeleteCommentUseCase deleteCommentUseCase;
    private final GetUserPostsUseCase getUserPostsUseCase;

    public PostController(CreatePostUseCase createPostUseCase,
                          UpdatePostUseCase updatePostUseCase,
                          DeletePostUseCase deletePostUseCase,
                          LikePostUseCase likePostUseCase,
                          UnlikePostUseCase unlikePostUseCase,
                          CommentPostUseCase commentPostUseCase,
                          GetPostCommentsUseCase getPostCommentsUseCase,
                          DeleteCommentUseCase deleteCommentUseCase,
                          GetUserPostsUseCase getUserPostsUseCase) {
        this.createPostUseCase = createPostUseCase;
        this.updatePostUseCase = updatePostUseCase;
        this.deletePostUseCase = deletePostUseCase;
        this.likePostUseCase = likePostUseCase;
        this.unlikePostUseCase = unlikePostUseCase;
        this.commentPostUseCase = commentPostUseCase;
        this.getPostCommentsUseCase = getPostCommentsUseCase;
        this.deleteCommentUseCase = deleteCommentUseCase;
        this.getUserPostsUseCase = getUserPostsUseCase;
    }

    @PostMapping
    @Override
    public ResponseEntity<PostResponse> createPost(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody CreatePostRequest req
    ) {
        var input = new CreatePostUseCase.Input(
                req.postImageUrl(), req.rate(), req.postText(),
                req.reviewTitle(), req.localName(),
                req.coordinateX(), req.coordinateY(), req.coordinateZ(),
                req.postBody()
        );
        var post = createPostUseCase.execute(user, input);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostResponse.from(post));
    }

    @PatchMapping("/{postId}")
    @Override
    public ResponseEntity<PostResponse> updatePost(
            @AuthenticationPrincipal User user,
            @PathVariable String postId,
            @Valid @RequestBody UpdatePostRequest req
    ) {
        var input = new UpdatePostUseCase.Input(
                req.postImageUrl(), req.rate(), req.postText(),
                req.reviewTitle(), req.localName(),
                req.coordinateX(), req.coordinateY(), req.coordinateZ(),
                req.postBody()
        );
        var post = updatePostUseCase.execute(user, postId, input);
        return ResponseEntity.ok(PostResponse.from(post));
    }

    @DeleteMapping("/{postId}")
    @Override
    public ResponseEntity<Void> deletePost(
            @AuthenticationPrincipal User user,
            @PathVariable String postId
    ) {
        deletePostUseCase.execute(user, postId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{postId}/like")
    @Override
    public ResponseEntity<PostLikeResponse> likePost(
            @AuthenticationPrincipal User user,
            @PathVariable String postId
    ) {
        var like = likePostUseCase.execute(user, postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostLikeResponse.from(like));
    }

    @DeleteMapping("/{postId}/like")
    @Override
    public ResponseEntity<Void> unlikePost(
            @AuthenticationPrincipal User user,
            @PathVariable String postId
    ) {
        unlikePostUseCase.execute(user, postId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{postId}/comments")
    @Override
    public ResponseEntity<PostCommentResponse> commentPost(
            @AuthenticationPrincipal User user,
            @PathVariable String postId,
            @Valid @RequestBody CreateCommentRequest req
    ) {
        var input = new CommentPostUseCase.Input(req.commentText(), req.responseTo());
        var comment = commentPostUseCase.execute(user, postId, input);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostCommentResponse.from(comment));
    }

    @GetMapping("/{postId}/comments")
    @Override
    public ResponseEntity<PageResponse<PostCommentTreeResponse>> getPostComments(
            @PathVariable String postId,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(PageResponse.from(getPostCommentsUseCase.execute(postId, pageable)));
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    @Override
    public ResponseEntity<Void> deleteComment(
            @AuthenticationPrincipal User user,
            @PathVariable String postId,
            @PathVariable String commentId) {
        deleteCommentUseCase.execute(user, commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    @Override
    public ResponseEntity<PageResponse<FeedPostResponse>> getUserPosts(
            @AuthenticationPrincipal User user,
            @PathVariable String userId,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(
                PageResponse.from(getUserPostsUseCase.execute(user, userId, pageable).map(FeedPostResponse::from))
        );
    }
}
