package src.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("from Person p join fetch p.documents d " +
            "where d.type='PASSPORT' and d.number=:passportNumber")
    Person getPersonByPassportNumber(@Param("passportNumber") String passportNumber);
}
