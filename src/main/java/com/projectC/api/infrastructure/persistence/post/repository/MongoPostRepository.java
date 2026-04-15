package com.projectC.api.infrastructure.persistence.post.repository;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.repository.PostRepository;
import com.projectC.api.infrastructure.persistence.post.mapper.PostMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class MongoPostRepository implements PostRepository {

    private final SpringDataPostRepository repository;
    private final PostMapper postMapper;

    public MongoPostRepository(SpringDataPostRepository repository, PostMapper postMapper) {
        this.repository = repository;
        this.postMapper = postMapper;
    }

    @Override
    public Post save(Post post) {
        return postMapper.toDomain(
                repository.save(postMapper.toDocument(post))
        );
    }

    @Override
    public Optional<Post> findById(String id) {
        return repository.findById(id)
                .map(postMapper::toDomain);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Post> findByUserIdOrderByCreatedAtDesc(String userId, Pageable pageable) {
        return repository.findByUserUserIdOrderByCreatedAtDesc(userId, pageable)
                .map(postMapper::toDomain);
    }

    @Override
    public Page<Post> findByUserIdInOrderByCreatedAtDesc(List<String> userIds, Pageable pageable) {
        return repository.findByUserUserIdInOrderByCreatedAtDesc(userIds, pageable)
                .map(postMapper::toDomain);
    }

    @Override
    public Page<Post> findByUserIdInAndCreatedAtAfter(List<String> userIds, LocalDateTime after, Pageable pageable) {
        return repository.findByUserUserIdInAndCreatedAtAfter(userIds, after, pageable)
                .map(postMapper::toDomain);
    }
}
