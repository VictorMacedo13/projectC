package com.projectC.api.infrastructure.persistence.report.repository;

import com.projectC.api.infrastructure.persistence.report.UserReportDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataUserReportRepository extends MongoRepository<UserReportDocument, String> {
}

