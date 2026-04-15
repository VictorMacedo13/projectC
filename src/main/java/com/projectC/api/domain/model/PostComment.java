package com.projectC.api.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostComment {
    private String id;
    private String postId;
    private String userCommentUserId;
    private String userCommentUsername;
    private String userCommentPictureUrl;
    private String userCommentName;
    private String commentText;
    private LocalDateTime createdAt;
    private String responseTo; // id do comentário pai (null se não for resposta)
}

