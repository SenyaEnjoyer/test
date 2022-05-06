package src.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.ContactType;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class ContactDto {

    private Long id;

    private ContactType type;

    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactDto)) return false;
        ContactDto that = (ContactDto) o;
        return type == that.type && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
