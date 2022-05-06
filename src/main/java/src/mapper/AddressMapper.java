package src.mapper;

import src.domain.Address;
import src.domain.Region;
import src.dto.AddressDto;

public class AddressMapper {

    public static Address dto2Entity(AddressDto dto) {
        Address entity = new Address();
        entity.setId(dto.getId());
        entity.setValue(dto.getValue());
        entity.setRegion(new Region(dto.getRegionName()));

        return entity;
    }

    public static AddressDto entity2Dto(Address entity) {
        AddressDto dto = new AddressDto();
        dto.setId(entity.getId());
        dto.setValue(entity.getValue());
        dto.setRegionName(entity.getRegion().getName());
        return dto;
    }
}
