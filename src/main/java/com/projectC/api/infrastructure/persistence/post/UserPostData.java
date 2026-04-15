package com.projectC.api.infrastructure.persistence.post;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
public class UserPostData {

    @Indexed
    private String userId;
    private String name;
    private String username;
    private Integer userScore;
    private String userProfilePictureUrl;
}

