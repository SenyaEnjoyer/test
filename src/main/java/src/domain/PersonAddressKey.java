package src.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonAddressKey implements Serializable {

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "address_id")
    private Long addressId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PersonAddressKey that = (PersonAddressKey) o;
        return Objects.equals(personId, that.personId)
                && Objects.equals(addressId, that.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, addressId);
    }
}
