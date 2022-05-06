package src.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PERSON_ADDRESS")
@NoArgsConstructor
@Getter
@Setter
public class PersonAddress {

    @EmbeddedId
    PersonAddressKey id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("addressId")
    @JoinColumn(name = "address_id")
    private Address address;

    private boolean registration;

    public PersonAddress(Person person,
                         Address address,
                         boolean registration) {
        this.person = person;
        this.address = address;
        this.id = new PersonAddressKey(person.getId(), address.getId());
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PersonAddress that = (PersonAddress) o;
        return Objects.equals(person, that.person)
                && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, address);
    }
}
