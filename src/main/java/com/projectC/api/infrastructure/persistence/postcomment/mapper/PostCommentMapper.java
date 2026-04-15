package com.projectC.api.infrastructure.persistence.postcomment.mapper;

import com.projectC.api.domain.model.PostComment;
import com.projectC.api.infrastructure.persistence.postcomment.PostCommentDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostCommentMapper {
    PostComment toDomain(PostCommentDocument doc);
    PostCommentDocument toDocument(PostComment comment);
}

