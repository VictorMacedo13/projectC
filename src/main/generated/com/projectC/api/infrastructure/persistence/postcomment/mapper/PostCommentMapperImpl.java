package com.projectC.api.infrastructure.persistence.postcomment.mapper;

import com.projectC.api.domain.model.PostComment;
import com.projectC.api.infrastructure.persistence.postcomment.PostCommentDocument;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-15T00:25:20-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class PostCommentMapperImpl implements PostCommentMapper {

    @Override
    public PostComment toDomain(PostCommentDocument doc) {
        if ( doc == null ) {
            return null;
        }

        PostComment postComment = new PostComment();

        postComment.setId( doc.getId() );
        postComment.setPostId( doc.getPostId() );
        postComment.setUserCommentUserId( doc.getUserCommentUserId() );
        postComment.setUserCommentUsername( doc.getUserCommentUsername() );
        postComment.setUserCommentPictureUrl( doc.getUserCommentPictureUrl() );
        postComment.setUserCommentName( doc.getUserCommentName() );
        postComment.setCommentText( doc.getCommentText() );
        postComment.setCreatedAt( doc.getCreatedAt() );
        postComment.setResponseTo( doc.getResponseTo() );

        return postComment;
    }

    @Override
    public PostCommentDocument toDocument(PostComment comment) {
        if ( comment == null ) {
            return null;
        }

        PostCommentDocument postCommentDocument = new PostCommentDocument();

        postCommentDocument.setId( comment.getId() );
        postCommentDocument.setPostId( comment.getPostId() );
        postCommentDocument.setUserCommentUserId( comment.getUserCommentUserId() );
        postCommentDocument.setUserCommentUsername( comment.getUserCommentUsername() );
        postCommentDocument.setUserCommentPictureUrl( comment.getUserCommentPictureUrl() );
        postCommentDocument.setUserCommentName( comment.getUserCommentName() );
        postCommentDocument.setCommentText( comment.getCommentText() );
        postCommentDocument.setCreatedAt( comment.getCreatedAt() );
        postCommentDocument.setResponseTo( comment.getResponseTo() );

        return postCommentDocument;
    }
}
