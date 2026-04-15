package com.projectC.api.application.report;

import com.projectC.api.domain.model.CommentReport;
import com.projectC.api.domain.repository.CommentReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetCommentReportsUseCase {
    private final CommentReportRepository repository;
    public GetCommentReportsUseCase(CommentReportRepository repository) { this.repository = repository; }
    public Page<CommentReport> execute(Pageable pageable) { return repository.findAllCommentReports(pageable); }
}
