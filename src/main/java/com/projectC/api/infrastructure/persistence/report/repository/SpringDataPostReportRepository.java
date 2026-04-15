package com.projectC.api.infrastructure.persistence.report.repository;

import com.projectC.api.infrastructure.persistence.report.PostReportDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataPostReportRepository extends MongoRepository<PostReportDocument, String> {
}

