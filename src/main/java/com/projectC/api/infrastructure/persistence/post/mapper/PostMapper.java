package com.projectC.api.infrastructure.persistence.post.mapper;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.model.PostUser;
import com.projectC.api.infrastructure.persistence.post.PostDocument;
import com.projectC.api.infrastructure.persistence.post.UserPostData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toDomain(PostDocument doc);
    PostDocument toDocument(Post post);

    PostUser toPostUser(UserPostData data);
    UserPostData toUserPostData(PostUser user);
}
