package result.agency.result_agency_intern.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import result.agency.result_agency_intern.entity.enums.OutcomeType;
import result.agency.result_agency_intern.entity.enums.PayType;
import result.agency.result_agency_intern.entity.enums.TransactionCondition;
import result.agency.result_agency_intern.entity.enums.TransactionType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SuperBuilder
public class Transaction extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private PayType payType;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private OutcomeType outcomeType; // Agar xarajat tanlangan bo'lsa

    @ManyToOne
    private Client client; // Agar daromad tanlangan bo'lsa

    @Enumerated(EnumType.STRING)
    private TransactionCondition transactionCondition; // Agar daromad tanlangan bo'lsa

    private String description; // Ixtiyoriy

    @OneToOne
    private Attachment attachment;



}
