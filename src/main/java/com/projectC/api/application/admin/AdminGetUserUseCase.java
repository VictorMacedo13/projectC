package com.projectC.api.application.admin;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class AdminGetUserUseCase {

    private final UserRepository userRepository;

    public AdminGetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String email, String username) {
        return userRepository.findByEmailOrUsername(email, username)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }
}

