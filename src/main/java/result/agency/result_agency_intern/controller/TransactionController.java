package result.agency.result_agency_intern.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import result.agency.result_agency_intern.dto.TransactionCreateDTO;
import result.agency.result_agency_intern.service.TransactionService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionCreateDTO transactionCreateDTO){
        return transactionService.createTransaction(transactionCreateDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id){
        return transactionService.delete(id);
    }

}
