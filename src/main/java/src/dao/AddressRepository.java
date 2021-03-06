package src.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
