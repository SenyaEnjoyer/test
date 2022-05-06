package src.service;

import org.springframework.data.domain.Page;
import src.domain.Person;
import src.dto.PersonDto;

import java.util.Optional;

public interface PersonService {

    void validatePerson(PersonDto personDto);

    Optional<PersonDto> createPerson(PersonDto personDto);
}
