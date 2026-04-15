package com.projectC.api.application.user;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ChangeNameUseCase {

    private final UserRepository userRepository;

    public ChangeNameUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user, String newName) {
        user.setName(newName);
        userRepository.save(user);
    }
}

