package com.projectC.api.application.user;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ChangeProfilePictureUseCase {

    private final UserRepository userRepository;

    public ChangeProfilePictureUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user, String profilePictureUrl) {
        user.setProfilePictureUrl(profilePictureUrl);
        userRepository.save(user);
    }
}

