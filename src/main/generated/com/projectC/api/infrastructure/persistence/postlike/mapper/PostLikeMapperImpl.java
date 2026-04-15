package com.projectC.api.infrastructure.persistence.postlike.mapper;

import com.projectC.api.domain.model.PostLike;
import com.projectC.api.infrastructure.persistence.postlike.PostLikeDocument;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-15T00:09:55-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class PostLikeMapperImpl implements PostLikeMapper {

    @Override
    public PostLike toDomain(PostLikeDocument doc) {
        if ( doc == null ) {
            return null;
        }

        PostLike postLike = new PostLike();

        postLike.setId( doc.getId() );
        postLike.setPostId( doc.getPostId() );
        postLike.setUserLikeId( doc.getUserLikeId() );
        postLike.setUserLikeUsername( doc.getUserLikeUsername() );
        postLike.setUserLikePictureUrl( doc.getUserLikePictureUrl() );
        postLike.setUserLikeName( doc.getUserLikeName() );

        return postLike;
    }

    @Override
    public PostLikeDocument toDocument(PostLike postLike) {
        if ( postLike == null ) {
            return null;
        }

        PostLikeDocument postLikeDocument = new PostLikeDocument();

        postLikeDocument.setId( postLike.getId() );
        postLikeDocument.setPostId( postLike.getPostId() );
        postLikeDocument.setUserLikeId( postLike.getUserLikeId() );
        postLikeDocument.setUserLikeUsername( postLike.getUserLikeUsername() );
        postLikeDocument.setUserLikePictureUrl( postLike.getUserLikePictureUrl() );
        postLikeDocument.setUserLikeName( postLike.getUserLikeName() );

        return postLikeDocument;
    }
}
