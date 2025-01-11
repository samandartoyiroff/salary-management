package result.agency.result_agency_intern.service;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import result.agency.result_agency_intern.dto.LoginDTO;

public interface AuthService {
    ResponseEntity<?> login(@Valid LoginDTO loginDTO);
}
