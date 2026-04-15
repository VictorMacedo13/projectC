package com.projectC.api.infrastructure.persistence.post;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("posts")
@CompoundIndexes({
        @CompoundIndex(def = "{'user.userId': 1, 'createdAt': -1}", name = "idx_post_user_created")
})
@Getter
@Setter
public class PostDocument {

    @Id
    private String id;
    private UserPostData user;
    private String postImageUrl;
    private Double rate;
    private String postText;
    private String reviewTitle;
    private String localName;
    private Double coordinateX;
    private Double coordinateY;
    private Double coordinateZ;
    private String postBody;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long likesCount = 0L;
    private Long commentsCount = 0L;
    private Long savesCount = 0L;
}
