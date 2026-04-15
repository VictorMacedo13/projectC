package com.projectC.api.application.post;

import com.projectC.api.domain.model.Post;

public record FeedPost(
        Post post,
        boolean likedByMe,
        boolean savedByMe
) {}

