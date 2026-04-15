package com.projectC.api.infrastructure.persistence.postcomment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("postComments")
@CompoundIndex(name = "postId_createdAt_idx", def = "{'postId': 1, 'createdAt': -1}")
@Getter
@Setter
public class PostCommentDocument {

    @Id
    private String id;
    @Indexed
    private String postId;
    @Indexed
    private String userCommentUserId;
    private String userCommentUsername;
    private String userCommentPictureUrl;
    private String userCommentName;
    private String commentText;
    private LocalDateTime createdAt;
    private String responseTo;
}

