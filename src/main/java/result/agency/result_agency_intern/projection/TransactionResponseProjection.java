package result.agency.result_agency_intern.projection;


import result.agency.result_agency_intern.entity.enums.PayType;

public interface TransactionResponseProjection {

    Long getId();
    String getTransactionType();
    String getCreatedDate();
    String getAmount();
    String getDescription();
    Long getFileId();
    String getOutcomeName();
    PayType getPayType();
    String getClientName();
    String getServiceName();
}
