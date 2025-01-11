package result.agency.result_agency_intern.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.dto.OutcomeNameRequestDTO;
import result.agency.result_agency_intern.dto.OutcomeNameUpdateDTO;
import result.agency.result_agency_intern.dto.OutcomeResponseDTO;
import result.agency.result_agency_intern.entity.OutcomeName;
import result.agency.result_agency_intern.repository.OutcomeNameRepository;
import result.agency.result_agency_intern.service.OutcomeNameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutcomeNameServiceImpl implements OutcomeNameService {

    private final OutcomeNameRepository outcomeNameRepository;

    @Override
    @Transactional
    public ResponseEntity<?> createOutcomeName(OutcomeNameRequestDTO outcomeNameRequest) {

        Optional<OutcomeName> optionalOutcomeName
                = outcomeNameRepository.findByNameAndDeletedFalse(outcomeNameRequest.getName());
        if (optionalOutcomeName.isEmpty()) {
            OutcomeName outcomeName = new OutcomeName();
            outcomeName.setName(outcomeNameRequest.getName());
            outcomeNameRepository.save(outcomeName);
            return ResponseEntity.ok("Saved outcome name successfully");
        }
        return ResponseEntity.badRequest().body("Outcome name already exists");

    }

    @Override
    public ResponseEntity<?> getAllOutcomes() {

        List<OutcomeName> outcomeNames = outcomeNameRepository.findAllByDeletedFalse();
        List<OutcomeResponseDTO> responseDTOS = new ArrayList<>();
        for (OutcomeName outcomeName : outcomeNames) {
            OutcomeResponseDTO outcomeResponseDTO = new OutcomeResponseDTO();
            outcomeResponseDTO.setName(outcomeName.getName());
            outcomeResponseDTO.setId(outcomeName.getId());
            responseDTOS.add(outcomeResponseDTO);
        }
        return ResponseEntity.ok(responseDTOS);

    }

    @Override
    @Transactional
    public ResponseEntity<?> updateOutcome(Long id, OutcomeNameUpdateDTO outcomeNameUpdateDTO) {

        Optional<OutcomeName> optionalOutcomeName = outcomeNameRepository.findByIdAndDeletedFalse(id);
        if (optionalOutcomeName.isPresent()) {
            OutcomeName outcomeName1 = optionalOutcomeName.get();
            Optional<OutcomeName> optionalOutcomeName1 = outcomeNameRepository.findByNameAndDeletedFalse(outcomeNameUpdateDTO.getName());
            if (optionalOutcomeName1.isPresent()) {
                OutcomeName outcomeName2 = optionalOutcomeName1.get();
                if (outcomeName2.getName().equals(outcomeNameUpdateDTO.getName())) return ResponseEntity.badRequest().body("Outcome name already exists");
            }

            outcomeName1.setName(outcomeNameUpdateDTO.getName());
            outcomeNameRepository.save(outcomeName1);
            return ResponseEntity.ok("Updated outcome name successfully");
        }
        return ResponseEntity.badRequest().body("Outcome name not found");

    }

    @Override
    public ResponseEntity<?> getOne(Long id) {

        Optional<OutcomeName> optionalOutcomeName = outcomeNameRepository.findByIdAndDeletedFalse(id);
        if (optionalOutcomeName.isPresent()) {
            OutcomeResponseDTO outcomeResponseDTO = new OutcomeResponseDTO();
            outcomeResponseDTO.setName(optionalOutcomeName.get().getName());
            outcomeResponseDTO.setId(optionalOutcomeName.get().getId());
            return ResponseEntity.ok(outcomeResponseDTO);
        }
        return ResponseEntity.badRequest().body("Outcome name not found");
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteOutcome(Long id) {
        Optional<OutcomeName> optionalOutcomeName = outcomeNameRepository.findByIdAndDeletedFalse(id);
        if (optionalOutcomeName.isPresent()) {
            OutcomeName outcomeName = optionalOutcomeName.get();
            outcomeName.setDeleted(true);
            outcomeNameRepository.save(outcomeName);
            return ResponseEntity.ok("Deleted outcome successfully");
        }
        throw new RuntimeException("Outcome name not found");
    }
}
