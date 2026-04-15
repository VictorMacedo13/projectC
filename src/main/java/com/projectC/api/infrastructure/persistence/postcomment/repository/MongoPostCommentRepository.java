package com.projectC.api.infrastructure.persistence.postcomment.repository;

import com.projectC.api.domain.model.PostComment;
import com.projectC.api.domain.repository.PostCommentRepository;
import com.projectC.api.infrastructure.persistence.postcomment.mapper.PostCommentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoPostCommentRepository implements PostCommentRepository {

    private final SpringDataPostCommentRepository repository;
    private final PostCommentMapper postCommentMapper;

    public MongoPostCommentRepository(SpringDataPostCommentRepository repository, PostCommentMapper postCommentMapper) {
        this.repository = repository;
        this.postCommentMapper = postCommentMapper;
    }

    @Override
    public PostComment save(PostComment comment) {
        return postCommentMapper.toDomain(
                repository.save(postCommentMapper.toDocument(comment))
        );
    }

    @Override
    public List<PostComment> findByPostId(String postId) {
        return repository.findByPostId(postId).stream()
                .map(postCommentMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<PostComment> findById(String id) {
        return repository.findById(id)
                .map(postCommentMapper::toDomain);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Page<PostComment> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(postCommentMapper::toDomain);
    }

    @Override
    public Page<PostComment> findByPostId(String postId, Pageable pageable) {
        return repository.findByPostId(postId, pageable)
                .map(postCommentMapper::toDomain);
    }

    @Override
    public Page<PostComment> findRootCommentsByPostId(String postId, Pageable pageable) {
        return repository.findByPostIdAndResponseToIsNull(postId, pageable)
                .map(postCommentMapper::toDomain);
    }

    @Override
    public List<PostComment> findRepliesByPostIdAndParentIds(String postId, List<String> parentIds) {
        return repository.findByPostIdAndResponseToIn(postId, parentIds).stream()
                .map(postCommentMapper::toDomain)
                .toList();
    }
}

