package com.projectC.api.infrastructure.persistence.follow.repository;

import com.projectC.api.infrastructure.persistence.follow.FollowDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringDataFollowRepository extends MongoRepository<FollowDocument, String> {
    boolean existsByFollowerAndFollowing(String follower, String following);
    void deleteByFollowerAndFollowing(String follower, String following);
    Page<FollowDocument> findByFollowing(String following, Pageable pageable);
    Page<FollowDocument> findByFollower(String follower, Pageable pageable);
    List<FollowDocument> findByFollower(String follower);
}
