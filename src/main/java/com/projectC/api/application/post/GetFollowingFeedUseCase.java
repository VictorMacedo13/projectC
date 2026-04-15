package com.projectC.api.application.post;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.FollowRepository;
import com.projectC.api.domain.repository.PostLikeRepository;
import com.projectC.api.domain.repository.PostRepository;
import com.projectC.api.domain.repository.SaveRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class GetFollowingFeedUseCase {

    private final FollowRepository followRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final SaveRepository saveRepository;

    public GetFollowingFeedUseCase(FollowRepository followRepository,
                                   PostRepository postRepository,
                                   PostLikeRepository postLikeRepository,
                                   SaveRepository saveRepository) {
        this.followRepository = followRepository;
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
        this.saveRepository = saveRepository;
    }

    public Page<FeedPost> execute(User user, Pageable pageable) {
        List<String> followingIds = followRepository.findFollowingIdsByFollower(user.getId());
        if (followingIds.isEmpty()) {
            return Page.empty(pageable);
        }

        Page<Post> posts = postRepository.findByUserIdInOrderByCreatedAtDesc(followingIds, pageable);
        List<String> postIds = posts.getContent().stream().map(Post::getId).toList();

        Set<String> likedPostIds = postLikeRepository.findLikedPostIdsByUser(user.getId(), postIds);
        Set<String> savedPostIds = saveRepository.findSavedPostIdsByUser(user.getId(), postIds);

        return posts.map(post -> new FeedPost(
                post,
                likedPostIds.contains(post.getId()),
                savedPostIds.contains(post.getId())
        ));
    }
}
