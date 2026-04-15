package com.projectC.api.infrastructure.api.admin.controller;

import com.projectC.api.application.admin.*;
import com.projectC.api.infrastructure.api.admin.model.request.AdminBanUserRequest;
import com.projectC.api.infrastructure.api.admin.model.request.AdminCreateUserRequest;
import com.projectC.api.infrastructure.api.admin.model.request.AdminEditUserRequest;
import com.projectC.api.infrastructure.api.admin.model.response.AdminCommentResponse;
import com.projectC.api.infrastructure.api.admin.model.response.AdminUserResponse;
import com.projectC.api.infrastructure.api.common.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController implements AdminAPI {

    private final AdminCreateUserUseCase adminCreateUserUseCase;
    private final AdminListUsersUseCase adminListUsersUseCase;
    private final AdminGetUserUseCase adminGetUserUseCase;
    private final AdminEditUserUseCase adminEditUserUseCase;
    private final AdminBanUserUseCase adminBanUserUseCase;
    private final AdminDeletePostUseCase adminDeletePostUseCase;
    private final AdminListCommentsUseCase adminListCommentsUseCase;
    private final AdminGetCommentUseCase adminGetCommentUseCase;
    private final AdminDeleteCommentUseCase adminDeleteCommentUseCase;

    public AdminController(AdminCreateUserUseCase adminCreateUserUseCase,
                           AdminListUsersUseCase adminListUsersUseCase,
                           AdminGetUserUseCase adminGetUserUseCase,
                           AdminEditUserUseCase adminEditUserUseCase,
                           AdminBanUserUseCase adminBanUserUseCase,
                           AdminDeletePostUseCase adminDeletePostUseCase,
                           AdminListCommentsUseCase adminListCommentsUseCase,
                           AdminGetCommentUseCase adminGetCommentUseCase,
                           AdminDeleteCommentUseCase adminDeleteCommentUseCase) {
        this.adminCreateUserUseCase = adminCreateUserUseCase;
        this.adminListUsersUseCase = adminListUsersUseCase;
        this.adminGetUserUseCase = adminGetUserUseCase;
        this.adminEditUserUseCase = adminEditUserUseCase;
        this.adminBanUserUseCase = adminBanUserUseCase;
        this.adminDeletePostUseCase = adminDeletePostUseCase;
        this.adminListCommentsUseCase = adminListCommentsUseCase;
        this.adminGetCommentUseCase = adminGetCommentUseCase;
        this.adminDeleteCommentUseCase = adminDeleteCommentUseCase;
    }

    @PostMapping("/users")
    @Override
    public ResponseEntity<AdminUserResponse> createUser(@Valid @RequestBody AdminCreateUserRequest req) {
        var user = adminCreateUserUseCase.execute(
                new AdminCreateUserUseCase.Input(req.email(), req.password(), req.name(), req.username(), req.roles())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(AdminUserResponse.from(user));
    }

    @GetMapping("/users")
    @Override
    public ResponseEntity<PageResponse<AdminUserResponse>> listUsers(@PageableDefault(size = 20, sort = "username") Pageable pageable) {
        return ResponseEntity.ok(PageResponse.from(adminListUsersUseCase.execute(pageable).map(AdminUserResponse::from)));
    }

    @GetMapping("/users/search")
    @Override
    public ResponseEntity<AdminUserResponse> getUser(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String username) {
        if ((email == null || email.isBlank()) && (username == null || username.isBlank())) {
            throw new IllegalArgumentException("Informe ao menos email ou username para a busca");
        }
        return ResponseEntity.ok(AdminUserResponse.from(adminGetUserUseCase.execute(email, username)));
    }

    @PutMapping("/users/{userId}")
    @Override
    public ResponseEntity<AdminUserResponse> editUser(
            @PathVariable String userId,
            @Valid @RequestBody AdminEditUserRequest req) {
        var user = adminEditUserUseCase.execute(userId,
                new AdminEditUserUseCase.Input(req.name(), req.username(), req.profilePictureUrl(), req.roles()));
        return ResponseEntity.ok(AdminUserResponse.from(user));
    }

    @PostMapping("/users/{userId}/ban")
    @Override
    public ResponseEntity<AdminUserResponse> banUser(
            @PathVariable String userId,
            @Valid @RequestBody AdminBanUserRequest req) {
        var user = adminBanUserUseCase.execute(userId, req.bannedUntil());
        return ResponseEntity.ok(AdminUserResponse.from(user));
    }

    @DeleteMapping("/users/{userId}/ban")
    @Override
    public ResponseEntity<AdminUserResponse> unbanUser(@PathVariable String userId) {
        var user = adminBanUserUseCase.execute(userId, null);
        return ResponseEntity.ok(AdminUserResponse.from(user));
    }

    @DeleteMapping("/posts/{postId}")
    @Override
    public ResponseEntity<Void> deletePost(@PathVariable String postId) {
        adminDeletePostUseCase.execute(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comments")
    @Override
    public ResponseEntity<PageResponse<AdminCommentResponse>> listComments(
            @RequestParam(required = false) String postId,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        Page<AdminCommentResponse> page;
        if (postId != null && !postId.isBlank()) {
            page = adminListCommentsUseCase.executeByPostId(postId, pageable).map(AdminCommentResponse::from);
        } else {
            page = adminListCommentsUseCase.execute(pageable).map(AdminCommentResponse::from);
        }
        return ResponseEntity.ok(PageResponse.from(page));
    }

    @GetMapping("/comments/{commentId}")
    @Override
    public ResponseEntity<AdminCommentResponse> getComment(@PathVariable String commentId) {
        return ResponseEntity.ok(AdminCommentResponse.from(adminGetCommentUseCase.execute(commentId)));
    }

    @DeleteMapping("/comments/{commentId}")
    @Override
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) {
        adminDeleteCommentUseCase.execute(commentId);
        return ResponseEntity.noContent().build();
    }
}

