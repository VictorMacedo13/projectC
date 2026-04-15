package com.projectC.api.infrastructure.persistence.postlike.mapper;

import com.projectC.api.domain.model.PostLike;
import com.projectC.api.infrastructure.persistence.postlike.PostLikeDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostLikeMapper {
    PostLike toDomain(PostLikeDocument doc);
    PostLikeDocument toDocument(PostLike postLike);
}

