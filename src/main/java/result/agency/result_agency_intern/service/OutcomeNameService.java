package result.agency.result_agency_intern.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import result.agency.result_agency_intern.dto.OutcomeNameRequestDTO;
import result.agency.result_agency_intern.dto.OutcomeNameUpdateDTO;

public interface OutcomeNameService {
    ResponseEntity<?> createOutcomeName(@Valid OutcomeNameRequestDTO outcomeNameRequest);

    ResponseEntity<?> getAllOutcomes();

    ResponseEntity<?> updateOutcome(Long id, @Valid OutcomeNameUpdateDTO outcomeNameUpdateDTO);

    ResponseEntity<?> getOne(Long id);

    ResponseEntity<?> deleteOutcome(Long id);
}
