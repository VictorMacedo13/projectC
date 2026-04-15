package com.projectC.api.application.user;

import com.projectC.api.domain.model.Follow;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.FollowRepository;
import com.projectC.api.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class FollowUserUseCase {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public FollowUserUseCase(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    public void execute(User follower, String targetUserId) {
        if (follower.getId().equals(targetUserId)) {
            throw new IllegalArgumentException("Você não pode seguir a si mesmo");
        }

        User target = userRepository.findById(targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (followRepository.existsByFollowerAndFollowing(follower.getId(), targetUserId)) {
            throw new IllegalArgumentException("Você já segue este usuário");
        }

        Follow follow = new Follow();
        follow.setFollower(follower.getId());
        follow.setFollowerUserName(follower.getUsername());
        follow.setFollowerName(follower.getName());
        follow.setFollowerUserPictureUrl(follower.getProfilePictureUrl());
        follow.setFollowing(target.getId());
        follow.setFollowingUserName(target.getUsername());
        follow.setFollowingName(target.getName());
        follow.setFollowingUserPictureUrl(target.getProfilePictureUrl());
        followRepository.save(follow);

        target.setFollowersCount(target.getFollowersCount() + 1);
        userRepository.save(target);

        follower.setFollowingCount(follower.getFollowingCount() + 1);
        userRepository.save(follower);
    }
}

