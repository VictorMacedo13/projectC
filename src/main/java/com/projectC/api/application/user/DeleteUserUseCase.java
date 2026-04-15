package com.projectC.api.application.user;

import com.projectC.api.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserUseCase {

    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(String userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        userRepository.deleteById(userId);
    }
}

