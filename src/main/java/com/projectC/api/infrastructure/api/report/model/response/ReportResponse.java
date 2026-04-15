package com.projectC.api.infrastructure.api.report.model.response;

import com.projectC.api.domain.model.PostReport;
import com.projectC.api.domain.model.CommentReport;
import com.projectC.api.domain.model.UserReport;

import java.time.LocalDateTime;

public record ReportResponse(
        String id,
        String targetId,
        String targetType,
        String reportCategory,
        String reportMessage,
        String reporterUserId,
        LocalDateTime createdAt
) {
    public static ReportResponse from(PostReport r) {
        return new ReportResponse(r.getId(), r.getPostId(), "POST",
                r.getReportCategory(), r.getReportMessage(), r.getReporterUserId(), r.getCreatedAt());
    }

    public static ReportResponse from(CommentReport r) {
        return new ReportResponse(r.getId(), r.getCommentId(), "COMMENT",
                r.getReportCategory(), r.getReportMessage(), r.getReporterUserId(), r.getCreatedAt());
    }

    public static ReportResponse from(UserReport r) {
        return new ReportResponse(r.getId(), r.getUserId(), "USER",
                r.getReportCategory(), r.getReportMessage(), r.getReporterUserId(), r.getCreatedAt());
    }
}

