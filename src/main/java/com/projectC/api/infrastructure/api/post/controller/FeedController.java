package com.projectC.api.infrastructure.api.post.controller;

import com.projectC.api.application.post.GetFollowing24hFeedUseCase;
import com.projectC.api.application.post.GetFollowingFeedUseCase;
import com.projectC.api.domain.model.User;
import com.projectC.api.infrastructure.api.common.PageResponse;
import com.projectC.api.infrastructure.api.post.model.response.FeedPostResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
public class FeedController implements FeedAPI {

    private final GetFollowingFeedUseCase getFollowingFeedUseCase;
    private final GetFollowing24hFeedUseCase getFollowing24hFeedUseCase;

    public FeedController(GetFollowingFeedUseCase getFollowingFeedUseCase,
                          GetFollowing24hFeedUseCase getFollowing24hFeedUseCase) {
        this.getFollowingFeedUseCase = getFollowingFeedUseCase;
        this.getFollowing24hFeedUseCase = getFollowing24hFeedUseCase;
    }

    @GetMapping("/following")
    @Override
    public ResponseEntity<PageResponse<FeedPostResponse>> getFollowingFeed(
            @AuthenticationPrincipal User user,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(
                PageResponse.from(getFollowingFeedUseCase.execute(user, pageable).map(FeedPostResponse::from))
        );
    }

    @GetMapping("/following/24h")
    @Override
    public ResponseEntity<PageResponse<FeedPostResponse>> getFollowing24hFeed(
            @AuthenticationPrincipal User user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(
                PageResponse.from(getFollowing24hFeedUseCase.execute(user, page, size).map(FeedPostResponse::from))
        );
    }
}
