package result.agency.result_agency_intern.dto;

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
public class UserCreateDTO {

    @NotBlank(message = "fullname cannot be blank")
    @NotNull(message = "fullname cannot be null")
    private String fullName;
    @NotBlank(message = "login cannot be blank")
    @NotNull(message = "login cannot be null")
    private String login;
    @NotBlank(message = "password cannot be blank")
    @NotNull(message = "password cannot be null")
    private String password;
    @NotNull(message = "roleId cannot be null")
    private Long roleId;

}
