package result.agency.result_agency_intern.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClientResponseDTO {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private String serviceName;

}
