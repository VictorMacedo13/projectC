package com.projectC.api.infrastructure.persistence.follow.mapper;

import com.projectC.api.domain.model.Follow;
import com.projectC.api.infrastructure.persistence.follow.FollowDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FollowMapper {
    Follow toDomain(FollowDocument doc);
    FollowDocument toDocument(Follow follow);
}

