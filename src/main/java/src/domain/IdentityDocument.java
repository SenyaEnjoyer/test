package src.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.IdentityDocumentType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "IDENTITY_DOCUMENT")
@NoArgsConstructor
@Getter
@Setter
public class IdentityDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private IdentityDocumentType type;

    private boolean main;

    private String number;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdentityDocument)) return false;
        IdentityDocument that = (IdentityDocument) o;
        return type == that.type && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, number);
    }
}
