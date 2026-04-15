package com.projectC.api.application.user;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.FollowRepository;
import com.projectC.api.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UnfollowUserUseCase {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public UnfollowUserUseCase(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    public void execute(User follower, String targetUserId) {
        if (follower.getId().equals(targetUserId)) {
            throw new IllegalArgumentException("Operação inválida");
        }

        if (!followRepository.existsByFollowerAndFollowing(follower.getId(), targetUserId)) {
            throw new IllegalArgumentException("Você não segue este usuário");
        }

        User target = userRepository.findById(targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        followRepository.deleteByFollowerAndFollowing(follower.getId(), targetUserId);

        target.setFollowersCount(Math.max(0, target.getFollowersCount() - 1));
        userRepository.save(target);

        follower.setFollowingCount(Math.max(0, follower.getFollowingCount() - 1));
        userRepository.save(follower);
    }
}

