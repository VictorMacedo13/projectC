package com.projectC.api.infrastructure.api.save.controller;

import com.projectC.api.application.save.GetUserSavesUseCase;
import com.projectC.api.application.save.SavePostUseCase;
import com.projectC.api.application.save.UnsavePostUseCase;
import com.projectC.api.domain.model.User;
import com.projectC.api.infrastructure.api.save.model.response.SaveResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saves")
public class SaveController implements SaveAPI {

    private final SavePostUseCase savePostUseCase;
    private final UnsavePostUseCase unsavePostUseCase;
    private final GetUserSavesUseCase getUserSavesUseCase;

    public SaveController(SavePostUseCase savePostUseCase,
                          UnsavePostUseCase unsavePostUseCase,
                          GetUserSavesUseCase getUserSavesUseCase) {
        this.savePostUseCase = savePostUseCase;
        this.unsavePostUseCase = unsavePostUseCase;
        this.getUserSavesUseCase = getUserSavesUseCase;
    }

    @PostMapping("/{postId}")
    @Override
    public ResponseEntity<SaveResponse> savePost(
            @AuthenticationPrincipal User user,
            @PathVariable String postId
    ) {
        var save = savePostUseCase.execute(user, postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SaveResponse.from(save));
    }

    @DeleteMapping("/{postId}")
    @Override
    public ResponseEntity<Void> unsavePost(
            @AuthenticationPrincipal User user,
            @PathVariable String postId
    ) {
        unsavePostUseCase.execute(user, postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    @Override
    public ResponseEntity<List<SaveResponse>> getUserSaves(@PathVariable String userId) {
        var saves = getUserSavesUseCase.execute(userId).stream()
                .map(SaveResponse::from)
                .toList();
        return ResponseEntity.ok(saves);
    }
}

