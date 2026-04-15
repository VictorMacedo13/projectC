package com.projectC.api.application.post;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.FollowRepository;
import com.projectC.api.domain.repository.PostLikeRepository;
import com.projectC.api.domain.repository.PostRepository;
import com.projectC.api.domain.repository.SaveRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
public class GetFollowing24hFeedUseCase {

    private final FollowRepository followRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final SaveRepository saveRepository;

    public GetFollowing24hFeedUseCase(FollowRepository followRepository,
                                      PostRepository postRepository,
                                      PostLikeRepository postLikeRepository,
                                      SaveRepository saveRepository) {
        this.followRepository = followRepository;
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
        this.saveRepository = saveRepository;
    }

    public Page<FeedPost> execute(User user, int page, int size) {
        List<String> followingIds = followRepository.findFollowingIdsByFollower(user.getId());
        if (followingIds.isEmpty()) {
            return Page.empty();
        }

        LocalDateTime since = LocalDateTime.now().minusHours(24);
        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Post> posts = postRepository.findByUserIdInAndCreatedAtAfter(followingIds, since, pageable);

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
