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
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClientUpdateRequestDTO {

    @NotNull(message = "fullname canoot be null")
    @NotBlank(message = "fullname canoot be blank")
    private String fullName;
    @NotNull(message = "phone canoot be null")
    @NotBlank(message = "phone canoot be blank")
    private String phoneNumber;
    @NotNull(message = "serviceId cannot be null")
    private Long serviceId;


}
