package com.projectC.api.infrastructure.persistence.save.repository;

import com.projectC.api.domain.model.Save;
import com.projectC.api.domain.repository.SaveRepository;
import com.projectC.api.infrastructure.persistence.save.mapper.SaveMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class MongoSaveRepository implements SaveRepository {

    private final SpringDataSaveRepository repository;
    private final SaveMapper saveMapper;

    public MongoSaveRepository(SpringDataSaveRepository repository, SaveMapper saveMapper) {
        this.repository = repository;
        this.saveMapper = saveMapper;
    }

    @Override
    public Save save(Save save) {
        return saveMapper.toDomain(
                repository.save(saveMapper.toDocument(save))
        );
    }

    @Override
    public List<Save> findByUserId(String userId) {
        return repository.findByUserId(userId).stream()
                .map(saveMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByPostIdAndUserId(String postId, String userId) {
        return repository.existsByPostIdAndUserId(postId, userId);
    }

    @Override
    public void deleteByPostIdAndUserId(String postId, String userId) {
        repository.deleteByPostIdAndUserId(postId, userId);
    }

    @Override
    public Set<String> findSavedPostIdsByUser(String userId, List<String> postIds) {
        return repository.findByUserIdAndPostIdIn(userId, postIds).stream()
                .map(doc -> doc.getPostId())
                .collect(Collectors.toSet());
    }
}
