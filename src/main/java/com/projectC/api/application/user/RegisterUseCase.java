package com.projectC.api.application.user;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterUseCase {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public RegisterUseCase(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public void execute(String email, String password, String name, String username) {

        if (repository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        if (repository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username já em uso");
        }

        var encoded = encoder.encode(password);
        var user = new User(email, encoded, name, username);

        if (repository.count() == 0) {
            user.setRoles(List.of("ADMIN", "USER"));
        }

        repository.save(user);
    }
}
