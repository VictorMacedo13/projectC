package com.projectC.api.infrastructure.persistence.user.mapper;

import com.projectC.api.domain.model.User;
import com.projectC.api.infrastructure.persistence.user.UserDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserDocument doc);

    UserDocument toDocument(User user);
}