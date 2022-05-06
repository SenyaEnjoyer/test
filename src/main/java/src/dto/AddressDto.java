package src.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddressDto {

    private Long id;

    private String value;

    @JsonProperty("region")
    private String regionName;

    private boolean registration;
}
