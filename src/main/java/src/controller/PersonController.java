package src.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.domain.Person;
import src.dto.EditPersonRequest;
import src.dto.PersonDto;
import src.exception.*;
import src.service.PersonService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // Создание данных о гражданине
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) {
        if (personDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            personService.validatePerson(personDto);
            Optional<PersonDto> createdPerson = personService.createPerson(personDto);
            if (createdPerson.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(createdPerson.get(), HttpStatus.CREATED);
        } catch (EmptyPersonException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RegionNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoRegistrationAddressException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (MultipleRegistrationAddressesException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoMainDocumentException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (MultipleMainDocumentsException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DuplicateDocumentException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
