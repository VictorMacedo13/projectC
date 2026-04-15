package com.projectC.api.application.post;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.PostLikeRepository;
import com.projectC.api.domain.repository.PostRepository;
import com.projectC.api.domain.repository.SaveRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class GetUserPostsUseCase {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final SaveRepository saveRepository;

    public GetUserPostsUseCase(PostRepository postRepository,
                               PostLikeRepository postLikeRepository,
                               SaveRepository saveRepository) {
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
        this.saveRepository = saveRepository;
    }

    public Page<FeedPost> execute(User loggedUser, String targetUserId, Pageable pageable) {
        Page<Post> posts = postRepository.findByUserIdOrderByCreatedAtDesc(targetUserId, pageable);

        List<String> postIds = posts.getContent().stream().map(Post::getId).toList();
        Set<String> likedPostIds = postLikeRepository.findLikedPostIdsByUser(loggedUser.getId(), postIds);
        Set<String> savedPostIds = saveRepository.findSavedPostIdsByUser(loggedUser.getId(), postIds);

        return posts.map(post -> new FeedPost(
                post,
                likedPostIds.contains(post.getId()),
                savedPostIds.contains(post.getId())
        ));
    }
}
