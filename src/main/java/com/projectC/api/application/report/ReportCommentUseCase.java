package com.projectC.api.application.report;

import com.projectC.api.domain.model.CommentReport;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.CommentReportRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReportCommentUseCase {

    private final CommentReportRepository repository;

    public ReportCommentUseCase(CommentReportRepository repository) {
        this.repository = repository;
    }

    public record Input(String reportCategory, String reportMessage) {}

    public CommentReport execute(User reporter, String commentId, Input input) {
        CommentReport report = new CommentReport();
        report.setCommentId(commentId);
        report.setReportCategory(input.reportCategory());
        report.setReportMessage(input.reportMessage());
        report.setReporterUserId(reporter.getId());
        report.setCreatedAt(LocalDateTime.now());
        return repository.save(report);
    }
}

