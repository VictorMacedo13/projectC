package com.projectC.api.infrastructure.persistence.post.mapper;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.model.PostUser;
import com.projectC.api.infrastructure.persistence.post.PostDocument;
import com.projectC.api.infrastructure.persistence.post.UserPostData;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-15T12:57:27-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post toDomain(PostDocument doc) {
        if ( doc == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( doc.getId() );
        post.setUser( toPostUser( doc.getUser() ) );
        post.setPostImageUrl( doc.getPostImageUrl() );
        post.setRate( doc.getRate() );
        post.setPostText( doc.getPostText() );
        post.setReviewTitle( doc.getReviewTitle() );
        post.setLocalName( doc.getLocalName() );
        post.setCoordinateX( doc.getCoordinateX() );
        post.setCoordinateY( doc.getCoordinateY() );
        post.setCoordinateZ( doc.getCoordinateZ() );
        post.setPostBody( doc.getPostBody() );
        post.setCreatedAt( doc.getCreatedAt() );
        post.setUpdatedAt( doc.getUpdatedAt() );
        post.setLikesCount( doc.getLikesCount() );
        post.setCommentsCount( doc.getCommentsCount() );
        post.setSavesCount( doc.getSavesCount() );

        return post;
    }

    @Override
    public PostDocument toDocument(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDocument postDocument = new PostDocument();

        postDocument.setId( post.getId() );
        postDocument.setUser( toUserPostData( post.getUser() ) );
        postDocument.setPostImageUrl( post.getPostImageUrl() );
        postDocument.setRate( post.getRate() );
        postDocument.setPostText( post.getPostText() );
        postDocument.setReviewTitle( post.getReviewTitle() );
        postDocument.setLocalName( post.getLocalName() );
        postDocument.setCoordinateX( post.getCoordinateX() );
        postDocument.setCoordinateY( post.getCoordinateY() );
        postDocument.setCoordinateZ( post.getCoordinateZ() );
        postDocument.setPostBody( post.getPostBody() );
        postDocument.setCreatedAt( post.getCreatedAt() );
        postDocument.setUpdatedAt( post.getUpdatedAt() );
        postDocument.setLikesCount( post.getLikesCount() );
        postDocument.setCommentsCount( post.getCommentsCount() );
        postDocument.setSavesCount( post.getSavesCount() );

        return postDocument;
    }

    @Override
    public PostUser toPostUser(UserPostData data) {
        if ( data == null ) {
            return null;
        }

        PostUser postUser = new PostUser();

        postUser.setUserId( data.getUserId() );
        postUser.setUsername( data.getUsername() );
        postUser.setUserScore( data.getUserScore() );
        postUser.setUserProfilePictureUrl( data.getUserProfilePictureUrl() );

        return postUser;
    }

    @Override
    public UserPostData toUserPostData(PostUser user) {
        if ( user == null ) {
            return null;
        }

        UserPostData userPostData = new UserPostData();

        userPostData.setUserId( user.getUserId() );
        userPostData.setUsername( user.getUsername() );
        userPostData.setUserScore( user.getUserScore() );
        userPostData.setUserProfilePictureUrl( user.getUserProfilePictureUrl() );

        return userPostData;
    }
}
