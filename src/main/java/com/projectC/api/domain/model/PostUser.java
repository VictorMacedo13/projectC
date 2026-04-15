package com.projectC.api.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostUser {
    private String userId;
    private String name;
    private String username;
    private Integer userScore;
    private String userProfilePictureUrl;
}

