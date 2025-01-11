package result.agency.result_agency_intern.service;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.dto.TransactionCreateDTO;

@Service
public interface TransactionService {
    ResponseEntity<?> createTransaction(@Valid TransactionCreateDTO transactionCreateDTO);

    ResponseEntity<?> getAllTransactions();

    ResponseEntity<?> delete(Long id);

}
