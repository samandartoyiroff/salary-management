package result.agency.result_agency_intern.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import result.agency.result_agency_intern.entity.enums.Currency;
import result.agency.result_agency_intern.entity.enums.PayType;
import result.agency.result_agency_intern.entity.enums.TransactionCondition;
import result.agency.result_agency_intern.entity.enums.TransactionType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TransactionCreateDTO {

    @NotNull(message = "transaction type cannot be null")
    private TransactionType transactionType;

    @NotNull(message = "PayType cannot be null")
    private PayType payType;

    @NotNull(message = "Amount cannot be null")
    private Double amount;

    @NotNull(message = "client id cannot be null")
    private Long clientId; // Agar Daromad tanlangan bo'sa null bo'lishi mumkin emas

    private LocalDateTime dateTime = LocalDateTime.now();

    private Long outcomeId; // Agar Xarajat tanlangan bo'sa null bo'lishi mumkin emas

    private TransactionCondition transactionCondition; // Agar Daromad tanlangan bo'sa null bo'lishi mumkin emas

    // Optional
    private String description;

    // Optional
    private Long attachmentId;

    @NotNull(message = "currency cannot be null")
    @NotBlank(message = "currency cannot be blank")
    private String currency;

}
