package com.projectC.api.domain.repository;

import com.projectC.api.domain.model.PostReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostReportRepository {
    PostReport save(PostReport report);
    Page<PostReport> findAllPostReports(Pageable pageable);
}
