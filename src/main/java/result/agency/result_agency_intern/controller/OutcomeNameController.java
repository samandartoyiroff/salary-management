package result.agency.result_agency_intern.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import result.agency.result_agency_intern.dto.OutcomeNameRequestDTO;
import result.agency.result_agency_intern.dto.OutcomeNameUpdateDTO;
import result.agency.result_agency_intern.service.OutcomeNameService;

@RestController
@RequestMapping("/api/v1/outcome-name")
@RequiredArgsConstructor
public class OutcomeNameController {

    private final OutcomeNameService outcomeNameService;

    @PostMapping
    public ResponseEntity<?> createOutcomeName(@Valid @RequestBody OutcomeNameRequestDTO outcomeNameRequest){
        return outcomeNameService.createOutcomeName(outcomeNameRequest);
    }

    @GetMapping
    public ResponseEntity<?> getAllOutcomeName(){
        return outcomeNameService.getAllOutcomes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOutcomeNameById(@PathVariable Long id){
        return outcomeNameService.getOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOutcomeName(@Valid @RequestBody OutcomeNameUpdateDTO outcomeNameUpdateDTO, @PathVariable Long id){
        return outcomeNameService.updateOutcome(id, outcomeNameUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOutcomeName(@PathVariable Long id){
        return outcomeNameService.deleteOutcome(id);
    }


}
