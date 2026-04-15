package com.projectC.api.application.admin;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdminBanUserUseCase {

    private final UserRepository userRepository;

    public AdminBanUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String userId, LocalDateTime bannedUntil) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (bannedUntil != null && bannedUntil.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data de banimento deve ser futura");
        }

        user.setBannedUntil(bannedUntil); // null = desbanir
        return userRepository.save(user);
    }
}


