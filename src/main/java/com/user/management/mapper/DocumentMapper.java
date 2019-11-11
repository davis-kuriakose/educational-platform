package com.user.management.mapper;

import com.user.management.domain.dto.DocumentDTO;
import com.user.management.domain.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    Document toDocument(DocumentDTO documentDTO);

    DocumentDTO toDTO(Document document);

}