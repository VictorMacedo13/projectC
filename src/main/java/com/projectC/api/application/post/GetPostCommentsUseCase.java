package com.projectC.api.application.post;

import com.projectC.api.domain.model.PostComment;
import com.projectC.api.domain.repository.PostCommentRepository;
import com.projectC.api.domain.repository.PostRepository;
import com.projectC.api.infrastructure.api.post.model.response.PostCommentTreeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GetPostCommentsUseCase {

    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;

    public GetPostCommentsUseCase(PostRepository postRepository, PostCommentRepository postCommentRepository) {
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
    }

    public Page<PostCommentTreeResponse> execute(String postId, Pageable pageable) {
        postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post não encontrado"));

        Page<PostComment> rootPage = postCommentRepository.findRootCommentsByPostId(postId, pageable);

        if (rootPage.isEmpty()) {
            return Page.empty(pageable);
        }

        List<String> rootIds = rootPage.getContent().stream()
                .map(PostComment::getId)
                .toList();

        Map<String, List<PostComment>> childrenByParentId = postCommentRepository
                .findRepliesByPostIdAndParentIds(postId, rootIds)
                .stream()
                .collect(Collectors.groupingBy(PostComment::getResponseTo));

        return rootPage.map(root -> buildTree(root, childrenByParentId));
    }

    private PostCommentTreeResponse buildTree(PostComment comment,
                                              Map<String, List<PostComment>> childrenByParentId) {
        List<PostCommentTreeResponse> replies = childrenByParentId
                .getOrDefault(comment.getId(), List.of())
                .stream()
                .map(child -> buildTree(child, childrenByParentId))
                .toList();
        return PostCommentTreeResponse.from(comment, replies);
    }
}

