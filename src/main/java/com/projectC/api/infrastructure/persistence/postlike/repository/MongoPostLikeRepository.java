package com.projectC.api.infrastructure.persistence.postlike.repository;

import com.projectC.api.domain.model.PostLike;
import com.projectC.api.domain.repository.PostLikeRepository;
import com.projectC.api.infrastructure.persistence.postlike.mapper.PostLikeMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class MongoPostLikeRepository implements PostLikeRepository {

    private final SpringDataPostLikeRepository repository;
    private final PostLikeMapper postLikeMapper;

    public MongoPostLikeRepository(SpringDataPostLikeRepository repository, PostLikeMapper postLikeMapper) {
        this.repository = repository;
        this.postLikeMapper = postLikeMapper;
    }

    @Override
    public PostLike save(PostLike postLike) {
        return postLikeMapper.toDomain(
                repository.save(postLikeMapper.toDocument(postLike))
        );
    }

    @Override
    public boolean existsByPostIdAndUserLikeId(String postId, String userLikeId) {
        return repository.existsByPostIdAndUserLikeId(postId, userLikeId);
    }

    @Override
    public void deleteByPostIdAndUserLikeId(String postId, String userLikeId) {
        repository.deleteByPostIdAndUserLikeId(postId, userLikeId);
    }

    @Override
    public Set<String> findLikedPostIdsByUser(String userId, List<String> postIds) {
        return repository.findByUserLikeIdAndPostIdIn(userId, postIds).stream()
                .map(doc -> doc.getPostId())
                .collect(Collectors.toSet());
    }
}
