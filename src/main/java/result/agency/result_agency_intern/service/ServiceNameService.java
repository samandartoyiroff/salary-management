package result.agency.result_agency_intern.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import result.agency.result_agency_intern.dto.ServiceCreateDTO;
import result.agency.result_agency_intern.dto.ServiceUpdateDTO;

public interface ServiceNameService {
    ResponseEntity<?> createService(@Valid ServiceCreateDTO serviceCreateDTO);

    ResponseEntity<?> getAllServices();

    ResponseEntity<?> getOneServiceName(Long id);

    ResponseEntity<?> updateServiceName(Long id, @Valid ServiceUpdateDTO serviceUpdateDTO);

    ResponseEntity<?> deleteById(Long id);
}
