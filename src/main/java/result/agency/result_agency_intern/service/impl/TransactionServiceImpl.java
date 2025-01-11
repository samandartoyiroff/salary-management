package result.agency.result_agency_intern.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import result.agency.result_agency_intern.dto.TransactionCreateDTO;
import result.agency.result_agency_intern.entity.Attachment;
import result.agency.result_agency_intern.entity.Client;
import result.agency.result_agency_intern.entity.OutcomeName;
import result.agency.result_agency_intern.entity.Transaction;
import result.agency.result_agency_intern.entity.enums.TransactionCondition;
import result.agency.result_agency_intern.entity.enums.TransactionType;
import result.agency.result_agency_intern.projection.TransactionResponseProjection;
import result.agency.result_agency_intern.repository.AttachmentRepository;
import result.agency.result_agency_intern.repository.ClientRepository;
import result.agency.result_agency_intern.repository.OutcomeNameRepository;
import result.agency.result_agency_intern.repository.TransactionRepository;
import result.agency.result_agency_intern.service.TransactionService;
import result.agency.result_agency_intern.util.CurrencyService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ClientRepository clientRepository;
    private final OutcomeNameRepository outcomeNameRepository;
    private final AttachmentRepository attachmentRepository;
    private final CurrencyService currencyService;

    @Override
    @Transactional
    public ResponseEntity<?> createTransaction(TransactionCreateDTO transactionCreateDTO) {

        Double amount = currencyService.toTransferUZS(transactionCreateDTO.getCurrency(), transactionCreateDTO.getAmount());

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionCreateDTO.getTransactionType());
        transaction.setPayType(transactionCreateDTO.getPayType());
        if (transactionCreateDTO.getTransactionType().equals(TransactionType.DAROMAD)){

            TransactionCondition transactionCondition = transactionCreateDTO.getTransactionCondition();
            if (transactionCondition==null) return ResponseEntity.badRequest()
                    .body("Siz daromadni tanladingiz va bu holatda tranzaksiya holati null bo'lishi mumkin emas!!!");
            transaction.setTransactionCondition(transactionCondition);

            Optional<Client> optionalClient = clientRepository.findByIdAndDeletedFalse(transactionCreateDTO.getClientId());
            if (optionalClient.isEmpty()) return ResponseEntity.badRequest().body("Client not found");
            else {
                Client client = optionalClient.get();
                transaction.setClient(client);
            }
        }
        else if (transactionCreateDTO.getTransactionType().equals(TransactionType.XARAJAT)){

            Optional<OutcomeName> optionalOutcomeName = outcomeNameRepository.findByIdAndDeletedFalse(transactionCreateDTO.getOutcomeId());
            if (optionalOutcomeName.isEmpty()) return ResponseEntity.badRequest().body("Outcome not found");
            OutcomeName outcomeName = optionalOutcomeName.get();
            transaction.setOutcomeName(outcomeName);
        }

        transaction.setCreatedAt(transactionCreateDTO.getDateTime());
        transaction.setDescription(transactionCreateDTO.getDescription());
        if (transactionCreateDTO.getAttachmentId()!=null){
            Long attachmentId = transactionCreateDTO.getAttachmentId();
            Optional<Attachment> optionalAttachment = attachmentRepository.findByIdAndDeletedFalse(attachmentId);
            if (optionalAttachment.isPresent()) {
                Attachment attachment = optionalAttachment.get();
                transaction.setAttachment(attachment);
            }
        }
        transactionRepository.save(transaction);
        return ResponseEntity.ok("Transaction created");

    }

    @Override
    public ResponseEntity<?> getAllTransactions() {
        List<TransactionResponseProjection> transactionList = transactionRepository.getAllTransactions();
        return ResponseEntity.ok(transactionList);

    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findByIdAndDeletedFalse(id);
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setDeleted(true);
            transactionRepository.save(transaction);
            return ResponseEntity.ok("Transaction deleted");
        }
        return ResponseEntity.badRequest().body("Transaction not found");
    }
}
