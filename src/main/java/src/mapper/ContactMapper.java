package src.mapper;

import src.domain.Contact;
import src.dto.ContactDto;

public class ContactMapper {

    public static Contact dto2Entity(ContactDto dto) {
        Contact entity = new Contact();
        entity.setId(dto.getId());
        entity.setType(dto.getType());
        entity.setValue(dto.getValue());
        return entity;
    }

    public static ContactDto entity2Dto(Contact entity) {
        ContactDto dto = new ContactDto();
        dto.setId(entity.getId());
        dto.setValue(entity.getValue());
        dto.setType(entity.getType());
        return dto;
    }
}
