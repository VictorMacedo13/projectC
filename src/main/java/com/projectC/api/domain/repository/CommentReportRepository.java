package com.projectC.api.domain.repository;

import com.projectC.api.domain.model.CommentReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentReportRepository {
    CommentReport save(CommentReport report);
    Page<CommentReport> findAllCommentReports(Pageable pageable);
}
