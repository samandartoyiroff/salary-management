package result.agency.result_agency_intern.service;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import result.agency.result_agency_intern.dto.CleintCreateDTO;
import result.agency.result_agency_intern.dto.ClientUpdateRequestDTO;

public interface ClientService {
    ResponseEntity<?> createClient(CleintCreateDTO cleintCreateDTO);

    ResponseEntity<?> getAllClients();

    ResponseEntity<?> getOneClient(Long id);

    ResponseEntity<?> updateClient(@Valid ClientUpdateRequestDTO clientUpdateRequest, Long id);

    ResponseEntity<?> deleteClient(Long id);
}
