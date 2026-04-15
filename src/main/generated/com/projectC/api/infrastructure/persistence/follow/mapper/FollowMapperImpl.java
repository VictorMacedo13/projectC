package com.projectC.api.infrastructure.persistence.follow.mapper;

import com.projectC.api.domain.model.Follow;
import com.projectC.api.infrastructure.persistence.follow.FollowDocument;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-14T20:43:34-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class FollowMapperImpl implements FollowMapper {

    @Override
    public Follow toDomain(FollowDocument doc) {
        if ( doc == null ) {
            return null;
        }

        Follow follow = new Follow();

        follow.setId( doc.getId() );
        follow.setFollower( doc.getFollower() );
        follow.setFollowerUserName( doc.getFollowerUserName() );
        follow.setFollowerName( doc.getFollowerName() );
        follow.setFollowerUserPictureUrl( doc.getFollowerUserPictureUrl() );
        follow.setFollowing( doc.getFollowing() );
        follow.setFollowingUserName( doc.getFollowingUserName() );
        follow.setFollowingName( doc.getFollowingName() );
        follow.setFollowingUserPictureUrl( doc.getFollowingUserPictureUrl() );

        return follow;
    }

    @Override
    public FollowDocument toDocument(Follow follow) {
        if ( follow == null ) {
            return null;
        }

        FollowDocument followDocument = new FollowDocument();

        followDocument.setId( follow.getId() );
        followDocument.setFollower( follow.getFollower() );
        followDocument.setFollowerUserName( follow.getFollowerUserName() );
        followDocument.setFollowerName( follow.getFollowerName() );
        followDocument.setFollowerUserPictureUrl( follow.getFollowerUserPictureUrl() );
        followDocument.setFollowing( follow.getFollowing() );
        followDocument.setFollowingUserName( follow.getFollowingUserName() );
        followDocument.setFollowingName( follow.getFollowingName() );
        followDocument.setFollowingUserPictureUrl( follow.getFollowingUserPictureUrl() );

        return followDocument;
    }
}
