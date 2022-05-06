package src.mapper;

import src.domain.IdentityDocument;
import src.dto.IdentityDocumentDto;

public class DocumentMapper {

    public static IdentityDocument dto2Entity(IdentityDocumentDto dto) {
        IdentityDocument entity = new IdentityDocument();
        entity.setId(dto.getId());
        entity.setType(dto.getType());
        entity.setNumber(dto.getNumber());
        entity.setMain(dto.isMain());
        return entity;
    }

    public static IdentityDocumentDto entity2Dto(IdentityDocument entity) {
        IdentityDocumentDto dto = new IdentityDocumentDto();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setNumber(entity.getNumber());
        dto.setMain(entity.isMain());
        return dto;
    }
}
