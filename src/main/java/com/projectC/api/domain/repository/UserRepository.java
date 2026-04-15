package com.projectC.api.domain.repository;

import com.projectC.api.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    void deleteById(String id);
    long count();
    Page<User> findAll(Pageable pageable);
    Optional<User> findByEmailOrUsername(String email, String username);
}
