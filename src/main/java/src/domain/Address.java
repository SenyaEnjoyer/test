package src.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ADDRESS")
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PersonAddress> people = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(value, address.value)
                && Objects.equals(region, address.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, region);
    }
}
