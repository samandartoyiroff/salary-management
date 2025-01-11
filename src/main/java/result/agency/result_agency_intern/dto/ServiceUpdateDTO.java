package result.agency.result_agency_intern.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUpdateDTO {

    @NotBlank(message = "name cannot be blank")
    @NotNull(message = "name cannot bve null")
    private String name;

}
