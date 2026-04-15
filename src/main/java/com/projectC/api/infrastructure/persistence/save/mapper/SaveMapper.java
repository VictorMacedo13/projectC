package com.projectC.api.infrastructure.persistence.save.mapper;

import com.projectC.api.domain.model.Save;
import com.projectC.api.infrastructure.persistence.save.SaveDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaveMapper {
    Save toDomain(SaveDocument doc);
    SaveDocument toDocument(Save save);
}

