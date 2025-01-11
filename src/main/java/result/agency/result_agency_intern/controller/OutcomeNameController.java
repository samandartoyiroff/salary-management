package result.agency.result_agency_intern.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import result.agency.result_agency_intern.dto.OutcomeNameRequestDTO;
import result.agency.result_agency_intern.dto.OutcomeNameUpdateDTO;
import result.agency.result_agency_intern.service.OutcomeNameService;

@RestController
@RequestMapping("/api/v1/outcome-name")
@RequiredArgsConstructor
public class OutcomeNameController {

    private final OutcomeNameService outcomeNameService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createOutcomeName(@Valid @RequestBody OutcomeNameRequestDTO outcomeNameRequest){
        return outcomeNameService.createOutcomeName(outcomeNameRequest);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllOutcomeName(){
        return outcomeNameService.getAllOutcomes();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOutcomeNameById(@PathVariable Long id){
        return outcomeNameService.getOne(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOutcomeName(@Valid @RequestBody OutcomeNameUpdateDTO outcomeNameUpdateDTO, @PathVariable Long id){
        return outcomeNameService.updateOutcome(id, outcomeNameUpdateDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOutcomeName(@PathVariable Long id){
        return outcomeNameService.deleteOutcome(id);
    }


}
