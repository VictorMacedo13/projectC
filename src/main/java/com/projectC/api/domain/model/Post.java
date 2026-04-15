package com.projectC.api.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Post {
    private String id;
    private PostUser user;
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

