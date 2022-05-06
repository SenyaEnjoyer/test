package src.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PersonDto {

    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    // todo use other type?
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date birthDate;

    private Set<ContactDto> contacts;

    private Set<IdentityDocumentDto> documents;

    private List<AddressDto> addresses;
}
