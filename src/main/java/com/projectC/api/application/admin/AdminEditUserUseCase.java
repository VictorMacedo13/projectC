package com.projectC.api.application.admin;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminEditUserUseCase {

    private final UserRepository userRepository;

    public AdminEditUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public record Input(String name, String username, String profilePictureUrl, List<String> roles) {}

    public User execute(String userId, Input input) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (input.name() != null && !input.name().isBlank()) {
            user.setName(input.name());
        }

        if (input.username() != null && !input.username().isBlank()
                && !input.username().equals(user.getUsername())) {
            if (userRepository.existsByUsername(input.username())) {
                throw new IllegalArgumentException("Username já em uso");
            }
            user.setUsername(input.username());
        }

        if (input.profilePictureUrl() != null) {
            user.setProfilePictureUrl(input.profilePictureUrl());
        }

        if (input.roles() != null && !input.roles().isEmpty()) {
            user.setRoles(input.roles());
        }

        return userRepository.save(user);
    }
}

