package com.projectC.api.application.report;

import com.projectC.api.domain.model.PostReport;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.PostReportRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReportPostUseCase {

    private final PostReportRepository repository;

    public ReportPostUseCase(PostReportRepository repository) {
        this.repository = repository;
    }

    public record Input(String reportCategory, String reportMessage) {}

    public PostReport execute(User reporter, String postId, Input input) {
        PostReport report = new PostReport();
        report.setPostId(postId);
        report.setReportCategory(input.reportCategory());
        report.setReportMessage(input.reportMessage());
        report.setReporterUserId(reporter.getId());
        report.setCreatedAt(LocalDateTime.now());
        return repository.save(report);
    }
}

