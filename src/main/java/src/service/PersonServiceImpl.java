package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.dao.*;
import src.domain.Contact;
import src.domain.IdentityDocument;
import src.domain.Person;
import src.domain.PersonAddress;
import src.dto.AddressDto;
import src.dto.IdentityDocumentDto;
import src.dto.PersonDto;
import src.exception.*;
import src.mapper.PersonMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final ContactRepository contactRepository;
    private final IdentityDocumentRepository identityDocumentRepository;
    private final AddressRepository addressRepository;
    private final RegionRepository regionRepository;
    private final PersonAddressRepository personAddressRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository,
                             ContactRepository contactRepository,
                             IdentityDocumentRepository identityDocumentRepository,
                             AddressRepository addressRepository,
                             RegionRepository regionRepository, PersonAddressRepository personAddressRepository) {
        this.personRepository = personRepository;
        this.contactRepository = contactRepository;
        this.identityDocumentRepository = identityDocumentRepository;
        this.addressRepository = addressRepository;
        this.regionRepository = regionRepository;
        this.personAddressRepository = personAddressRepository;
    }


    @Override
    public void validatePerson(PersonDto personDto) {
        if (personDto == null) {
            throw new EmptyPersonException();
        }

        if (personDto.getAddresses() != null) {
            // check that regions exists
            personDto.getAddresses().forEach(address -> {
                if (!regionRepository.existsByName(address.getRegionName())) {
                    throw new RegionNotFoundException();
                }
            });

            // check that the registration address is the only one
            long registrationAddressesCounter = personDto.getAddresses()
                    .stream()
                    .filter(AddressDto::isRegistration)
                    .count();
            if (registrationAddressesCounter == 0) {
                throw new NoRegistrationAddressException();
            }
            if (registrationAddressesCounter > 1) {
                throw new MultipleRegistrationAddressesException();
            }
        }

        if (personDto.getDocuments() != null) {
            // check that the main identity document is the only one
            long mainDocumentCounter = personDto.getDocuments()
                    .stream()
                    .filter(IdentityDocumentDto::isMain)
                    .count();
            if (mainDocumentCounter == 0) {
                throw new NoMainDocumentException();
            }
            if (mainDocumentCounter > 1) {
                throw new MultipleMainDocumentsException();
            }

            // check that person with such documents doesn't exist
            if (personDto.getDocuments().stream()
                    .anyMatch(document -> identityDocumentRepository
                            .existsByTypeEqualsAndNumberEquals(document.getType(), document.getNumber()))) {
                throw new DuplicateDocumentException();
            }
        }
    }

    @Override
    @Transactional
    public Optional<PersonDto> createPerson(PersonDto personDto) {
        Person person = PersonMapper.dto2Entity(personDto);

        Set<IdentityDocument> documents = person.getDocuments();
        Set<Contact> contacts = person.getContacts();
        List<PersonAddress> addresses = person.getAddresses();

        Person createdPerson = personRepository.save(person);
        if (documents != null) {
            documents.forEach(document -> document.setPerson(createdPerson));
            identityDocumentRepository.saveAll(documents);
        }

        if (contacts != null) {
            contacts.forEach(contact -> contact.setPerson(createdPerson));
            contactRepository.saveAll(contacts);
        }

//        addresses.forEach(address -> address.setPerson(createdPerson));
        personAddressRepository.saveAll(addresses);

        return Optional.of(PersonMapper.entity2Dto(createdPerson));
    }
}
