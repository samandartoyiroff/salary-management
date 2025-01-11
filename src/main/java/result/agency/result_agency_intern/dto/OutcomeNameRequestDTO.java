package result.agency.result_agency_intern.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OutcomeNameRequestDTO {

    @NotBlank(message = "name cannot be blank")
    @NotNull(message = "name cannot be null")
    private String name;

}
