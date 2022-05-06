package src.mapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import src.domain.Person;
import src.domain.PersonAddress;
import src.dto.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {

    public static Person dto2Entity(PersonDto dto) {
        Person entity = new Person();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setBirthDate(dto.getBirthDate());

        if (dto.getContacts() != null) {
            entity.setContacts(dto.getContacts()
                    .stream()
                    .map(ContactMapper::dto2Entity)
                    .collect(Collectors.toSet())
            );
            entity.getContacts().forEach(contact -> contact.setPerson(entity));
        }

        if (dto.getDocuments() != null) {
            entity.setDocuments(dto.getDocuments()
                    .stream()
                    .map(DocumentMapper::dto2Entity)
                    .collect(Collectors.toSet())
            );
            entity.getDocuments().forEach(document -> document.setPerson(entity));
        }

        if (dto.getAddresses() != null) {
            List<PersonAddress> personAddressList = dto.getAddresses()
                    .stream()
                    .map(addressDto -> new PersonAddress(entity,
                            AddressMapper.dto2Entity(addressDto),
                            addressDto.isRegistration())
                    )
                    .collect(Collectors.toList());
            entity.setAddresses(personAddressList);
            entity.getAddresses().forEach(address -> address.getAddress().setPeople(entity.getAddresses()));
        }

        return entity;
    }

    public static PersonDto entity2Dto(Person entity) {
        PersonDto dto = new PersonDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        dto.setBirthDate(entity.getBirthDate());

        if (entity.getContacts() != null) {
            dto.setContacts(entity.getContacts()
                    .stream()
                    .map(ContactMapper::entity2Dto)
                    .collect(Collectors.toSet()));
        }

        if (entity.getDocuments() != null) {
            dto.setDocuments(entity.getDocuments()
                    .stream()
                    .map(DocumentMapper::entity2Dto)
                    .collect(Collectors.toSet()));
        }

        if (entity.getAddresses() != null) {
            dto.setAddresses(entity.getAddresses()
                    .stream()
                    .map(personAddress -> AddressMapper.entity2Dto(personAddress.getAddress()))
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}
