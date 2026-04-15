package com.projectC.api.infrastructure.persistence.user.repository;

import com.projectC.api.infrastructure.persistence.user.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface SpringDataUserRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByEmail(String email);
    Optional<UserDocument> findByUsername(String username);
    boolean existsByUsername(String username);

    @Query("{ $or: [ { email: ?0 }, { username: ?1 } ] }")
    Optional<UserDocument> findByEmailOrUsername(String email, String username);
}

