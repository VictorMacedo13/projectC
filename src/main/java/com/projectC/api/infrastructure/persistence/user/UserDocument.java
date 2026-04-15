package com.projectC.api.infrastructure.persistence.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("users")
@CompoundIndex(name = "email_username_idx", def = "{'email': 1, 'username': 1}")
@Getter
@Setter
public class UserDocument {

    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String name;
    @Indexed(unique = true)
    private String username;
    private String profilePictureUrl;
    private Long followersCount = 0L;
    private Long followingCount = 0L;
    private Integer score = 0;
    private Long PostsCount = 0L;
    private Long savedPoints = 0L;
    private List<String> roles;
    @Indexed
    private LocalDateTime bannedUntil;
}