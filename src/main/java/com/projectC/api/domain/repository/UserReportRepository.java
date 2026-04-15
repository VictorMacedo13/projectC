package com.projectC.api.domain.repository;

import com.projectC.api.domain.model.UserReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserReportRepository {
    UserReport save(UserReport report);
    Page<UserReport> findAllUserReports(Pageable pageable);
}
