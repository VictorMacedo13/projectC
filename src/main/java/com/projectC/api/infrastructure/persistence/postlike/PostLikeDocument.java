package com.projectC.api.infrastructure.persistence.postlike;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("postLikes")
@CompoundIndex(def = "{'postId': 1, 'userLikeId': 1}", unique = true)
@Getter
@Setter
public class PostLikeDocument {

    @Id
    private String id;
    private String postId;
    private String userLikeId;
    private String userLikeUsername;
    private String userLikePictureUrl;
    private String userLikeName;
}

