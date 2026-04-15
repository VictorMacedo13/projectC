package com.projectC.api.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
public class UserReport {
    private String id;
    private String userId;
    private String reportCategory;
    private String reportMessage;
    private String reporterUserId;
    private LocalDateTime createdAt;
}

