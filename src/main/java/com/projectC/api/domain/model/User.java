package com.projectC.api.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String id;
    private String email;
    private String password;
    private String name;
    private String username;
    private String profilePictureUrl;
    private Long followersCount = 0L;
    private Long followingCount = 0L;
    private Integer score = 0;
    private Long PostsCount = 0L;
    private Long savedPoints = 0L;
    private List<String> roles;
    private LocalDateTime bannedUntil;

    public User(String email, String password, String name, String username) {
        this.email = email;
        this.password = password;
        this.roles = List.of("USER");
        this.name = name;
        this.username = username;
    }
}
