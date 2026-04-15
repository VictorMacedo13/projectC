package com.projectC.api.infrastructure.api.report.controller;

import com.projectC.api.application.report.*;
import com.projectC.api.domain.model.User;
import com.projectC.api.infrastructure.api.common.PageResponse;
import com.projectC.api.infrastructure.api.report.model.request.ReportRequest;
import com.projectC.api.infrastructure.api.report.model.response.ReportResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController implements ReportAPI {

    private final ReportPostUseCase reportPostUseCase;
    private final ReportCommentUseCase reportCommentUseCase;
    private final ReportUserUseCase reportUserUseCase;
    private final GetPostReportsUseCase getPostReportsUseCase;
    private final GetCommentReportsUseCase getCommentReportsUseCase;
    private final GetUserReportsUseCase getUserReportsUseCase;

    public ReportController(ReportPostUseCase reportPostUseCase,
                            ReportCommentUseCase reportCommentUseCase,
                            ReportUserUseCase reportUserUseCase,
                            GetPostReportsUseCase getPostReportsUseCase,
                            GetCommentReportsUseCase getCommentReportsUseCase,
                            GetUserReportsUseCase getUserReportsUseCase) {
        this.reportPostUseCase = reportPostUseCase;
        this.reportCommentUseCase = reportCommentUseCase;
        this.reportUserUseCase = reportUserUseCase;
        this.getPostReportsUseCase = getPostReportsUseCase;
        this.getCommentReportsUseCase = getCommentReportsUseCase;
        this.getUserReportsUseCase = getUserReportsUseCase;
    }

    @PostMapping("/posts/{postId}")
    @Override
    public ResponseEntity<ReportResponse> reportPost(@AuthenticationPrincipal User user,
                                                     @PathVariable String postId,
                                                     @Valid @RequestBody ReportRequest req) {
        var report = reportPostUseCase.execute(user, postId,
                new ReportPostUseCase.Input(req.reportCategory(), req.reportMessage()));
        return ResponseEntity.status(HttpStatus.CREATED).body(ReportResponse.from(report));
    }

    @PostMapping("/comments/{commentId}")
    @Override
    public ResponseEntity<ReportResponse> reportComment(@AuthenticationPrincipal User user,
                                                        @PathVariable String commentId,
                                                        @Valid @RequestBody ReportRequest req) {
        var report = reportCommentUseCase.execute(user, commentId,
                new ReportCommentUseCase.Input(req.reportCategory(), req.reportMessage()));
        return ResponseEntity.status(HttpStatus.CREATED).body(ReportResponse.from(report));
    }

    @PostMapping("/users/{targetUserId}")
    @Override
    public ResponseEntity<ReportResponse> reportUser(@AuthenticationPrincipal User user,
                                                     @PathVariable String targetUserId,
                                                     @Valid @RequestBody ReportRequest req) {
        var report = reportUserUseCase.execute(user, targetUserId,
                new ReportUserUseCase.Input(req.reportCategory(), req.reportMessage()));
        return ResponseEntity.status(HttpStatus.CREATED).body(ReportResponse.from(report));
    }

    @GetMapping("/posts")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<PageResponse<ReportResponse>> getPostReports(
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(
                PageResponse.from(getPostReportsUseCase.execute(pageable).map(ReportResponse::from))
        );
    }

    @GetMapping("/comments")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<PageResponse<ReportResponse>> getCommentReports(
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(
                PageResponse.from(getCommentReportsUseCase.execute(pageable).map(ReportResponse::from))
        );
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<PageResponse<ReportResponse>> getUserReports(
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(
                PageResponse.from(getUserReportsUseCase.execute(pageable).map(ReportResponse::from))
        );
    }
}
