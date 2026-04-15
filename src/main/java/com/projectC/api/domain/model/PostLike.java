package com.projectC.api.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostLike {
    private String id;
    private String postId;
    private String userLikeId;
    private String userLikeUsername;
    private String userLikePictureUrl;
    private String userLikeName;
}

