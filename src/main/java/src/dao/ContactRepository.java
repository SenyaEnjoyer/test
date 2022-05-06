package src.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.domain.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
