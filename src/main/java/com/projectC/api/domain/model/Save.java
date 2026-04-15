package com.projectC.api.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Save {
    private String id;
    private String postId;
    private String userId;
    private Double coordinateX;
    private Double coordinateY;
    private Double coordinateZ;
    private String userPictureUrl;
    private String postImageUrl;
    private String postTitle;
    private String postLocalName;
}

