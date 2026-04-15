package com.projectC.api.application.save;

import com.projectC.api.domain.model.Save;
import com.projectC.api.domain.repository.SaveRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetUserSavesUseCase {

    private final SaveRepository saveRepository;

    public GetUserSavesUseCase(SaveRepository saveRepository) {
        this.saveRepository = saveRepository;
    }

    public List<Save> execute(String userId) {
        return saveRepository.findByUserId(userId);
    }
}

