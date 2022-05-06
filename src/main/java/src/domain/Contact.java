package src.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.ContactType;

import javax.persistence.*;

@Entity
@Table(name = "CONTACT")
@NoArgsConstructor
@Getter
@Setter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ContactType type;

    private String value;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}
