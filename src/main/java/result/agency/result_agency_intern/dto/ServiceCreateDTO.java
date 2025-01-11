package result.agency.result_agency_intern.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class ServiceCreateDTO {

    @NonNull
    @NotBlank(message="name cannot be blank")
    private String name;

}
