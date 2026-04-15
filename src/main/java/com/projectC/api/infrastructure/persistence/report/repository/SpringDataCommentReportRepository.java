package com.projectC.api.infrastructure.persistence.report.repository;

import com.projectC.api.infrastructure.persistence.report.CommentReportDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataCommentReportRepository extends MongoRepository<CommentReportDocument, String> {
}

