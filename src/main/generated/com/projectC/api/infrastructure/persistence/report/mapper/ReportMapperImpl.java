package com.projectC.api.infrastructure.persistence.report.mapper;

import com.projectC.api.domain.model.CommentReport;
import com.projectC.api.domain.model.PostReport;
import com.projectC.api.domain.model.UserReport;
import com.projectC.api.infrastructure.persistence.report.CommentReportDocument;
import com.projectC.api.infrastructure.persistence.report.PostReportDocument;
import com.projectC.api.infrastructure.persistence.report.UserReportDocument;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-15T00:57:25-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class ReportMapperImpl implements ReportMapper {

    @Override
    public PostReport toDomain(PostReportDocument doc) {
        if ( doc == null ) {
            return null;
        }

        PostReport postReport = new PostReport();

        postReport.setId( doc.getId() );
        postReport.setPostId( doc.getPostId() );
        postReport.setReportCategory( doc.getReportCategory() );
        postReport.setReportMessage( doc.getReportMessage() );
        postReport.setReporterUserId( doc.getReporterUserId() );
        postReport.setCreatedAt( doc.getCreatedAt() );

        return postReport;
    }

    @Override
    public PostReportDocument toDocument(PostReport report) {
        if ( report == null ) {
            return null;
        }

        PostReportDocument postReportDocument = new PostReportDocument();

        postReportDocument.setId( report.getId() );
        postReportDocument.setPostId( report.getPostId() );
        postReportDocument.setReportCategory( report.getReportCategory() );
        postReportDocument.setReportMessage( report.getReportMessage() );
        postReportDocument.setReporterUserId( report.getReporterUserId() );
        postReportDocument.setCreatedAt( report.getCreatedAt() );

        return postReportDocument;
    }

    @Override
    public CommentReport toDomain(CommentReportDocument doc) {
        if ( doc == null ) {
            return null;
        }

        CommentReport commentReport = new CommentReport();

        commentReport.setId( doc.getId() );
        commentReport.setCommentId( doc.getCommentId() );
        commentReport.setReportCategory( doc.getReportCategory() );
        commentReport.setReportMessage( doc.getReportMessage() );
        commentReport.setReporterUserId( doc.getReporterUserId() );
        commentReport.setCreatedAt( doc.getCreatedAt() );

        return commentReport;
    }

    @Override
    public CommentReportDocument toDocument(CommentReport report) {
        if ( report == null ) {
            return null;
        }

        CommentReportDocument commentReportDocument = new CommentReportDocument();

        commentReportDocument.setId( report.getId() );
        commentReportDocument.setCommentId( report.getCommentId() );
        commentReportDocument.setReportCategory( report.getReportCategory() );
        commentReportDocument.setReportMessage( report.getReportMessage() );
        commentReportDocument.setReporterUserId( report.getReporterUserId() );
        commentReportDocument.setCreatedAt( report.getCreatedAt() );

        return commentReportDocument;
    }

    @Override
    public UserReport toDomain(UserReportDocument doc) {
        if ( doc == null ) {
            return null;
        }

        UserReport userReport = new UserReport();

        userReport.setId( doc.getId() );
        userReport.setUserId( doc.getUserId() );
        userReport.setReportCategory( doc.getReportCategory() );
        userReport.setReportMessage( doc.getReportMessage() );
        userReport.setReporterUserId( doc.getReporterUserId() );
        userReport.setCreatedAt( doc.getCreatedAt() );

        return userReport;
    }

    @Override
    public UserReportDocument toDocument(UserReport report) {
        if ( report == null ) {
            return null;
        }

        UserReportDocument userReportDocument = new UserReportDocument();

        userReportDocument.setId( report.getId() );
        userReportDocument.setUserId( report.getUserId() );
        userReportDocument.setReportCategory( report.getReportCategory() );
        userReportDocument.setReportMessage( report.getReportMessage() );
        userReportDocument.setReporterUserId( report.getReporterUserId() );
        userReportDocument.setCreatedAt( report.getCreatedAt() );

        return userReportDocument;
    }
}
