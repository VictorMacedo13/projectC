package com.projectC.api.domain.repository;

import com.projectC.api.domain.model.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowRepository {
    Follow save(Follow follow);
    boolean existsByFollowerAndFollowing(String follower, String following);
    void deleteByFollowerAndFollowing(String follower, String following);
    Page<Follow> findByFollowing(String following, Pageable pageable);
    Page<Follow> findByFollower(String follower, Pageable pageable);
    List<String> findFollowingIdsByFollower(String follower);
}
