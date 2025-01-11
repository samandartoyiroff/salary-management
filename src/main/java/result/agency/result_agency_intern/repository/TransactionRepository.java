package result.agency.result_agency_intern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import result.agency.result_agency_intern.dto.MonthlyIncomeReportDTO;
import result.agency.result_agency_intern.dto.MonthlyOutcomeReportDTO;
import result.agency.result_agency_intern.dto.PayTypeReportDTO;
import result.agency.result_agency_intern.dto.TransactionTypeReportDTO;
import result.agency.result_agency_intern.entity.Transaction;
import result.agency.result_agency_intern.projection.TransactionResponseProjection;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(nativeQuery = true, value = """
        select
            t.id,
            t.transaction_type,
            t.created_at as created_date,
            t.amount,
            t.description,
            t.attachment_id as file_id,
            ou_n.name as outcome_name,
            t.pay_type,
            c.full_name as client_name,
            sn.name as service_name
            from transaction t
        left join client c on t.client_id = c.id and c.deleted=false
        left join attachment a on a.id=t.attachment_id and a.deleted=false
        left join outcome_name ou_n on t.outcome_name_id = ou_n.id and ou_n.deleted=false
        left join service_name sn on c.service_name_id = sn.id and sn.deleted=false
        where t.deleted=false
        
        """)
    List<TransactionResponseProjection> getAllTransactions();

    Optional<Transaction> findByIdAndDeletedFalse(Long id);


    @Query(nativeQuery = true, value = """
            select
                TO_CHAR(t.created_at, 'YYYY.MM') AS month,
                sum(t.amount) as income
                from transaction t
                where t.transaction_type='DAROMAD' and t.deleted=false
                group by month
            
            """)
    List<MonthlyIncomeReportDTO> getMonthlyIncome();


    @Query(nativeQuery = true, value = """
            select
                TO_CHAR(t.created_at, 'YYYY.MM') AS month,
                sum(t.amount) as outcome
                from transaction t
                where t.transaction_type='XARAJAT' and t.deleted=false
                group by month
            
            """)
    List<MonthlyOutcomeReportDTO> getMonthlyOutcome();

    @Query(nativeQuery = true, value = """

            select
            t.transaction_type,
            sum(t.amount) as amount
            from transaction t where t.deleted=false\s
            group by t.transaction_type
        
        """)
    List<TransactionTypeReportDTO> getReportByTransactionType();

    @Query(nativeQuery = true, value = """
            select
                t.pay_type,
                sum(t.amount) as amount
            from transaction t where t.deleted=false
            group by t.pay_type;
            """)
    List<PayTypeReportDTO> getDataByPayType();

}