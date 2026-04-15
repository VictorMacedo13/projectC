package com.projectC.api.infrastructure.persistence.report;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("reportsPosts")
@Getter @Setter
public class PostReportDocument {
    @Id private String id;
    private String postId;
    private String reportCategory;
    private String reportMessage;
    private String reporterUserId;
    private LocalDateTime createdAt;
}

