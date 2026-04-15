package com.projectC.api.infrastructure.persistence.user.repository;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.UserRepository;
import com.projectC.api.infrastructure.persistence.user.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoUserRepository implements UserRepository {

    private final SpringDataUserRepository repository;
    private final UserMapper userMapper;

    public MongoUserRepository(SpringDataUserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        return userMapper.toDomain(
                repository.save(userMapper.toDocument(user))
        );
    }

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(userMapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmailOrUsername(String email, String username) {
        return repository.findByEmailOrUsername(email, username)
                .map(userMapper::toDomain);
    }
}
