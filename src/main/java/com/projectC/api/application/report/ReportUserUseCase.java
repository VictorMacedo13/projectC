package com.projectC.api.application.report;

import com.projectC.api.domain.model.User;
import com.projectC.api.domain.model.UserReport;
import com.projectC.api.domain.repository.UserReportRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReportUserUseCase {

    private final UserReportRepository repository;

    public ReportUserUseCase(UserReportRepository repository) {
        this.repository = repository;
    }

    public record Input(String reportCategory, String reportMessage) {}

    public UserReport execute(User reporter, String targetUserId, Input input) {
        UserReport report = new UserReport();
        report.setUserId(targetUserId);
        report.setReportCategory(input.reportCategory());
        report.setReportMessage(input.reportMessage());
        report.setReporterUserId(reporter.getId());
        report.setCreatedAt(LocalDateTime.now());
        return repository.save(report);
    }
}

