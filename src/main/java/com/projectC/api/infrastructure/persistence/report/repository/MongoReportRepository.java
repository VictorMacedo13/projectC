package com.projectC.api.infrastructure.persistence.report.repository;

import com.projectC.api.domain.model.CommentReport;
import com.projectC.api.domain.model.PostReport;
import com.projectC.api.domain.model.UserReport;
import com.projectC.api.domain.repository.CommentReportRepository;
import com.projectC.api.domain.repository.PostReportRepository;
import com.projectC.api.domain.repository.UserReportRepository;
import com.projectC.api.infrastructure.persistence.report.mapper.ReportMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class MongoReportRepository implements PostReportRepository, CommentReportRepository, UserReportRepository {

    private final SpringDataPostReportRepository postReportRepo;
    private final SpringDataCommentReportRepository commentReportRepo;
    private final SpringDataUserReportRepository userReportRepo;
    private final ReportMapper reportMapper;

    public MongoReportRepository(SpringDataPostReportRepository postReportRepo,
                                 SpringDataCommentReportRepository commentReportRepo,
                                 SpringDataUserReportRepository userReportRepo,
                                 ReportMapper reportMapper) {
        this.postReportRepo = postReportRepo;
        this.commentReportRepo = commentReportRepo;
        this.userReportRepo = userReportRepo;
        this.reportMapper = reportMapper;
    }

    @Override public PostReport save(PostReport report) {
        return reportMapper.toDomain(postReportRepo.save(reportMapper.toDocument(report)));
    }
    @Override public Page<PostReport> findAllPostReports(Pageable pageable) {
        return postReportRepo.findAll(pageable).map(reportMapper::toDomain);
    }

    @Override public CommentReport save(CommentReport report) {
        return reportMapper.toDomain(commentReportRepo.save(reportMapper.toDocument(report)));
    }
    @Override public Page<CommentReport> findAllCommentReports(Pageable pageable) {
        return commentReportRepo.findAll(pageable).map(reportMapper::toDomain);
    }

    @Override public UserReport save(UserReport report) {
        return reportMapper.toDomain(userReportRepo.save(reportMapper.toDocument(report)));
    }
    @Override public Page<UserReport> findAllUserReports(Pageable pageable) {
        return userReportRepo.findAll(pageable).map(reportMapper::toDomain);
    }
}
