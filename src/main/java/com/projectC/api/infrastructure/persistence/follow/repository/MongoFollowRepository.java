package com.projectC.api.infrastructure.persistence.follow.repository;

import com.projectC.api.domain.model.Follow;
import com.projectC.api.domain.repository.FollowRepository;
import com.projectC.api.infrastructure.persistence.follow.FollowDocument;
import com.projectC.api.infrastructure.persistence.follow.mapper.FollowMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoFollowRepository implements FollowRepository {

    private final SpringDataFollowRepository repository;
    private final FollowMapper followMapper;

    public MongoFollowRepository(SpringDataFollowRepository repository, FollowMapper followMapper) {
        this.repository = repository;
        this.followMapper = followMapper;
    }

    @Override
    public Follow save(Follow follow) {
        return followMapper.toDomain(
                repository.save(followMapper.toDocument(follow))
        );
    }

    @Override
    public boolean existsByFollowerAndFollowing(String follower, String following) {
        return repository.existsByFollowerAndFollowing(follower, following);
    }

    @Override
    public void deleteByFollowerAndFollowing(String follower, String following) {
        repository.deleteByFollowerAndFollowing(follower, following);
    }

    @Override
    public Page<Follow> findByFollowing(String following, Pageable pageable) {
        return repository.findByFollowing(following, pageable)
                .map(followMapper::toDomain);
    }

    @Override
    public Page<Follow> findByFollower(String follower, Pageable pageable) {
        return repository.findByFollower(follower, pageable)
                .map(followMapper::toDomain);
    }

    @Override
    public List<String> findFollowingIdsByFollower(String follower) {
        return repository.findByFollower(follower).stream()
                .map(FollowDocument::getFollowing)
                .toList();
    }
}
