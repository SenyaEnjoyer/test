package src.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.domain.Person;

@NoArgsConstructor
@Getter
@Setter
public class EditPersonRequest {

    private Person person;

    private boolean hide;
}
