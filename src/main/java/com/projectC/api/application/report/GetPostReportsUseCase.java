package com.projectC.api.application.report;

import com.projectC.api.domain.model.PostReport;
import com.projectC.api.domain.repository.PostReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetPostReportsUseCase {
    private final PostReportRepository repository;
    public GetPostReportsUseCase(PostReportRepository repository) { this.repository = repository; }
    public Page<PostReport> execute(Pageable pageable) { return repository.findAllPostReports(pageable); }
}
