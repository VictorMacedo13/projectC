package com.projectC.api.infrastructure.api.user.controller;

import com.projectC.api.application.user.ChangeNameUseCase;
import com.projectC.api.application.user.ChangePasswordUseCase;
import com.projectC.api.application.user.ChangeProfilePictureUseCase;
import com.projectC.api.application.user.DeleteUserUseCase;
import com.projectC.api.application.user.FollowUserUseCase;
import com.projectC.api.application.user.GetFollowersUseCase;
import com.projectC.api.application.user.GetFollowingUseCase;
import com.projectC.api.application.user.UnfollowUserUseCase;
import com.projectC.api.domain.model.User;
import com.projectC.api.infrastructure.api.user.model.request.ChangeNameRequest;
import com.projectC.api.infrastructure.api.user.model.request.ChangePasswordRequest;
import com.projectC.api.infrastructure.api.user.model.request.ChangeProfilePictureRequest;
import com.projectC.api.infrastructure.api.common.PageResponse;
import com.projectC.api.infrastructure.api.user.model.response.FollowerResponse;
import com.projectC.api.infrastructure.api.user.model.response.FollowingResponse;
import com.projectC.api.infrastructure.api.user.model.response.UserMeResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController implements UserAPI {

    private final ChangePasswordUseCase changePasswordUseCase;
    private final ChangeNameUseCase changeNameUseCase;
    private final ChangeProfilePictureUseCase changeProfilePictureUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final FollowUserUseCase followUserUseCase;
    private final UnfollowUserUseCase unfollowUserUseCase;
    private final GetFollowersUseCase getFollowersUseCase;
    private final GetFollowingUseCase getFollowingUseCase;

    public UserController(ChangePasswordUseCase changePasswordUseCase,
                          ChangeNameUseCase changeNameUseCase,
                          ChangeProfilePictureUseCase changeProfilePictureUseCase,
                          DeleteUserUseCase deleteUserUseCase,
                          FollowUserUseCase followUserUseCase,
                          UnfollowUserUseCase unfollowUserUseCase,
                          GetFollowersUseCase getFollowersUseCase,
                          GetFollowingUseCase getFollowingUseCase) {
        this.changePasswordUseCase = changePasswordUseCase;
        this.changeNameUseCase = changeNameUseCase;
        this.changeProfilePictureUseCase = changeProfilePictureUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.followUserUseCase = followUserUseCase;
        this.unfollowUserUseCase = unfollowUserUseCase;
        this.getFollowersUseCase = getFollowersUseCase;
        this.getFollowingUseCase = getFollowingUseCase;
    }

    @GetMapping("/me")
    @Override
    public ResponseEntity<UserMeResponse> getMe(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(UserMeResponse.from(user));
    }

    @PatchMapping("/me/password")
    @Override
    public ResponseEntity<Void> changePassword(@AuthenticationPrincipal User user, @Valid @RequestBody ChangePasswordRequest req) {
        changePasswordUseCase.execute(user, req.currentPassword(), req.newPassword());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/me/name")
    @Override
    public ResponseEntity<Void> changeName(@AuthenticationPrincipal User user, @Valid @RequestBody ChangeNameRequest req) {
        changeNameUseCase.execute(user, req.name());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/me/picture")
    @Override
    public ResponseEntity<Void> changeProfilePicture(@AuthenticationPrincipal User user, @Valid @RequestBody ChangeProfilePictureRequest req) {
        changeProfilePictureUseCase.execute(user, req.profilePictureUrl());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/follow")
    @Override
    public ResponseEntity<Void> followUser(@AuthenticationPrincipal User user, @PathVariable String userId) {
        followUserUseCase.execute(user, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/follow")
    @Override
    public ResponseEntity<Void> unfollowUser(@AuthenticationPrincipal User user, @PathVariable String userId) {
        unfollowUserUseCase.execute(user, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me/followers/count")
    @Override
    public ResponseEntity<Long> getFollowersCount(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user.getFollowersCount());
    }

    @GetMapping("/me/following/count")
    @Override
    public ResponseEntity<Long> getFollowingCount(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user.getFollowingCount());
    }

    @GetMapping("/me/followers")
    @Override
    public ResponseEntity<PageResponse<FollowerResponse>> getFollowers(
            @AuthenticationPrincipal User user,
            @PageableDefault(size = 20, sort = "followerUserName") Pageable pageable) {
        return ResponseEntity.ok(
                PageResponse.from(getFollowersUseCase.execute(user.getId(), pageable).map(FollowerResponse::from))
        );
    }

    @GetMapping("/me/following")
    @Override
    public ResponseEntity<PageResponse<FollowingResponse>> getFollowing(
            @AuthenticationPrincipal User user,
            @PageableDefault(size = 20, sort = "followingUserName") Pageable pageable) {
        return ResponseEntity.ok(
                PageResponse.from(getFollowingUseCase.execute(user.getId(), pageable).map(FollowingResponse::from))
        );
    }

    @DeleteMapping("/{userId}/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        deleteUserUseCase.execute(userId);
        return ResponseEntity.noContent().build();
    }
}

