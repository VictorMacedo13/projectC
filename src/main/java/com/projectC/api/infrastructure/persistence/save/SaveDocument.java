package com.projectC.api.infrastructure.persistence.save;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("saves")
@CompoundIndex(def = "{'postId': 1, 'userId': 1}", unique = true)
@Getter
@Setter
public class SaveDocument {

    @Id
    private String id;
    private String postId;
    @Indexed
    private String userId;
    private Double coordinateX;
    private Double coordinateY;
    private Double coordinateZ;
    private String userPictureUrl;
    private String postImageUrl;
    private String postTitle;
    private String postLocalName;
}

