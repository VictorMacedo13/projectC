package com.projectC.api.application.admin;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminCreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminCreateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public record Input(String email, String password, String name, String username, List<String> roles) {}

    public User execute(Input input) {
        if (userRepository.findByEmail(input.email()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if (userRepository.existsByUsername(input.username())) {
            throw new IllegalArgumentException("Username já em uso");
        }

        var user = new User(input.email(), passwordEncoder.encode(input.password()), input.name(), input.username());

        if (input.roles() != null && !input.roles().isEmpty()) {
            user.setRoles(input.roles());
        }

        return userRepository.save(user);
    }
}

