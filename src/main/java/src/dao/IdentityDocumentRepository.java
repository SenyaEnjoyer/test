package src.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.IdentityDocumentType;
import src.domain.IdentityDocument;

@Repository
public interface IdentityDocumentRepository extends JpaRepository<IdentityDocument, Long> {
    boolean existsByTypeEqualsAndNumberEquals(@Param("type") IdentityDocumentType type, @Param("number") String number);
}
