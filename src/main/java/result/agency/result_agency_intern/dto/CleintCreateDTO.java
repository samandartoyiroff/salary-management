package result.agency.result_agency_intern.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CleintCreateDTO {

    @NotBlank(message = "fullname cannot be blank")
    @NotNull(message = "fullname cannot be null")
    private String fullName;
    @NotBlank(message = "phone cannot be blank")
    @NotNull(message = "phone cannot be null")
    private String phone;
    @NotNull(message = "serviceId cannot be null")
    private Long serviceId;

}
