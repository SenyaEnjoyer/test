package src.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.domain.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    boolean existsByName(String name);
}
