package com.projectC.api.infrastructure.persistence.user.mapper;

import com.projectC.api.domain.model.User;
import com.projectC.api.infrastructure.persistence.user.UserDocument;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-14T20:43:34-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toDomain(UserDocument doc) {
        if ( doc == null ) {
            return null;
        }

        User user = new User();

        user.setId( doc.getId() );
        user.setEmail( doc.getEmail() );
        user.setPassword( doc.getPassword() );
        user.setName( doc.getName() );
        user.setUsername( doc.getUsername() );
        user.setProfilePictureUrl( doc.getProfilePictureUrl() );
        user.setFollowersCount( doc.getFollowersCount() );
        user.setFollowingCount( doc.getFollowingCount() );
        user.setScore( doc.getScore() );
        user.setPostsCount( doc.getPostsCount() );
        user.setSavedPoints( doc.getSavedPoints() );
        List<String> list = doc.getRoles();
        if ( list != null ) {
            user.setRoles( new ArrayList<String>( list ) );
        }

        return user;
    }

    @Override
    public UserDocument toDocument(User user) {
        if ( user == null ) {
            return null;
        }

        UserDocument userDocument = new UserDocument();

        userDocument.setId( user.getId() );
        userDocument.setEmail( user.getEmail() );
        userDocument.setPassword( user.getPassword() );
        userDocument.setName( user.getName() );
        userDocument.setUsername( user.getUsername() );
        userDocument.setProfilePictureUrl( user.getProfilePictureUrl() );
        userDocument.setFollowersCount( user.getFollowersCount() );
        userDocument.setFollowingCount( user.getFollowingCount() );
        userDocument.setScore( user.getScore() );
        userDocument.setPostsCount( user.getPostsCount() );
        userDocument.setSavedPoints( user.getSavedPoints() );
        List<String> list = user.getRoles();
        if ( list != null ) {
            userDocument.setRoles( new ArrayList<String>( list ) );
        }

        return userDocument;
    }
}
