package com.projectC.api.application.user;

import com.projectC.api.domain.model.Follow;
import com.projectC.api.domain.repository.FollowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetFollowersUseCase {

    private final FollowRepository followRepository;

    public GetFollowersUseCase(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public Page<Follow> execute(String userId, Pageable pageable) {
        return followRepository.findByFollowing(userId, pageable);
    }
}
