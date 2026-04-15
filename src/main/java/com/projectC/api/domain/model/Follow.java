package com.projectC.api.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Follow {
    private String id;
    private String follower;
    private String followerUserName;
    private String followerName;
    private String followerUserPictureUrl;
    private String following;
    private String followingUserName;
    private String followingName;
    private String followingUserPictureUrl;
}

