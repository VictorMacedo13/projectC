package com.projectC.api.infrastructure.persistence.report.mapper;

import com.projectC.api.domain.model.CommentReport;
import com.projectC.api.domain.model.PostReport;
import com.projectC.api.domain.model.UserReport;
import com.projectC.api.infrastructure.persistence.report.CommentReportDocument;
import com.projectC.api.infrastructure.persistence.report.PostReportDocument;
import com.projectC.api.infrastructure.persistence.report.UserReportDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    PostReport toDomain(PostReportDocument doc);
    PostReportDocument toDocument(PostReport report);

    CommentReport toDomain(CommentReportDocument doc);
    CommentReportDocument toDocument(CommentReport report);

    UserReport toDomain(UserReportDocument doc);
    UserReportDocument toDocument(UserReport report);
}

