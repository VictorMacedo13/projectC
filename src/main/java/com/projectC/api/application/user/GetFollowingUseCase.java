package com.projectC.api.application.user;

import com.projectC.api.domain.model.Follow;
import com.projectC.api.domain.repository.FollowRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetFollowingUseCase {

    private final FollowRepository followRepository;

    public GetFollowingUseCase(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public Page<Follow> execute(String userId, Pageable pageable) {
        return followRepository.findByFollower(userId, pageable);
    }
}
