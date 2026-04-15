package com.projectC.api.application.report;

import com.projectC.api.domain.model.UserReport;
import com.projectC.api.domain.repository.UserReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetUserReportsUseCase {
    private final UserReportRepository repository;
    public GetUserReportsUseCase(UserReportRepository repository) { this.repository = repository; }
    public Page<UserReport> execute(Pageable pageable) { return repository.findAllUserReports(pageable); }
}
