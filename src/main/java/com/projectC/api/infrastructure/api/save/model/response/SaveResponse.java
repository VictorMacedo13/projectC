package com.projectC.api.infrastructure.api.save.model.response;

import com.projectC.api.domain.model.Save;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Post salvo")
public record SaveResponse(
        String id,
        String postId,
        String userId,
        Double coordinateX,
        Double coordinateY,
        Double coordinateZ,
        String userPictureUrl,
        String postImageUrl,
        String postTitle,
        String postLocalName
) {
    public static SaveResponse from(Save save) {
        return new SaveResponse(
                save.getId(),
                save.getPostId(),
                save.getUserId(),
                save.getCoordinateX(),
                save.getCoordinateY(),
                save.getCoordinateZ(),
                save.getUserPictureUrl(),
                save.getPostImageUrl(),
                save.getPostTitle(),
                save.getPostLocalName()
        );
    }
}

