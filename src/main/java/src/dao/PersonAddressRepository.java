package src.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.domain.PersonAddress;
import src.domain.PersonAddressKey;

@Repository
public interface PersonAddressRepository extends JpaRepository<PersonAddress, PersonAddressKey> {
}
