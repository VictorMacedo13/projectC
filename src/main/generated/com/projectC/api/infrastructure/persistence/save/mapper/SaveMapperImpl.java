package com.projectC.api.infrastructure.persistence.save.mapper;

import com.projectC.api.domain.model.Save;
import com.projectC.api.infrastructure.persistence.save.SaveDocument;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-15T00:44:07-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class SaveMapperImpl implements SaveMapper {

    @Override
    public Save toDomain(SaveDocument doc) {
        if ( doc == null ) {
            return null;
        }

        Save save = new Save();

        save.setId( doc.getId() );
        save.setPostId( doc.getPostId() );
        save.setUserId( doc.getUserId() );
        save.setCoordinateX( doc.getCoordinateX() );
        save.setCoordinateY( doc.getCoordinateY() );
        save.setCoordinateZ( doc.getCoordinateZ() );
        save.setUserPictureUrl( doc.getUserPictureUrl() );
        save.setPostImageUrl( doc.getPostImageUrl() );
        save.setPostTitle( doc.getPostTitle() );
        save.setPostLocalName( doc.getPostLocalName() );

        return save;
    }

    @Override
    public SaveDocument toDocument(Save save) {
        if ( save == null ) {
            return null;
        }

        SaveDocument saveDocument = new SaveDocument();

        saveDocument.setId( save.getId() );
        saveDocument.setPostId( save.getPostId() );
        saveDocument.setUserId( save.getUserId() );
        saveDocument.setCoordinateX( save.getCoordinateX() );
        saveDocument.setCoordinateY( save.getCoordinateY() );
        saveDocument.setCoordinateZ( save.getCoordinateZ() );
        saveDocument.setUserPictureUrl( save.getUserPictureUrl() );
        saveDocument.setPostImageUrl( save.getPostImageUrl() );
        saveDocument.setPostTitle( save.getPostTitle() );
        saveDocument.setPostLocalName( save.getPostLocalName() );

        return saveDocument;
    }
}
