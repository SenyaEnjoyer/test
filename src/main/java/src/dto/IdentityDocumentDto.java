package src.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.IdentityDocumentType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class IdentityDocumentDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType type;

    private String number;

    private boolean main;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdentityDocumentDto)) return false;
        IdentityDocumentDto that = (IdentityDocumentDto) o;
        return type == that.type && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, number);
    }
}
