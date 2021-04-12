package br.com.payments.paymentstransactions.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {

    @JsonProperty(value = "account_id")
    private String accountID;

    @JsonProperty(value = "operation_type_id")
    private String operationTypeId;

    private Double amount;
}
