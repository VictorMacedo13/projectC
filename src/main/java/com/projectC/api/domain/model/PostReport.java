package com.projectC.api.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
public class PostReport {
    private String id;
    private String postId;
    private String reportCategory;
    private String reportMessage;
    private String reporterUserId;
    private LocalDateTime createdAt;
}

