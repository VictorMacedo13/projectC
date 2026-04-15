package com.projectC.api.infrastructure.persistence.report;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("reportsUsers")
@Getter @Setter
public class UserReportDocument {
    @Id private String id;
    private String userId;
    private String reportCategory;
    private String reportMessage;
    private String reporterUserId;
    private LocalDateTime createdAt;
}

