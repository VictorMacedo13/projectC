package com.projectC.api.application.admin;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class AdminListUsersUseCase {

    private final UserRepository userRepository;

    public AdminListUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> execute(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}

