package com.projectC.api.infrastructure.persistence.follow;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("follows")
@CompoundIndex(def = "{'follower': 1, 'following': 1}", unique = true)
@Getter
@Setter
public class FollowDocument {

    @Id
    private String id;
    @Indexed
    private String follower;
    private String followerUserName;
    private String followerName;
    private String followerUserPictureUrl;
    @Indexed
    private String following;
    private String followingUserName;
    private String followingName;
    private String followingUserPictureUrl;
}

