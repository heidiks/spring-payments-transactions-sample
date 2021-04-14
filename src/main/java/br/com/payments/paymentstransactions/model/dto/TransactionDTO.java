package br.com.payments.paymentstransactions.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {

    @JsonProperty(value = "account_id")
    private Long accountID;

    @JsonProperty(value = "operation_type_id")
    private Long operationTypeId;

    private Double amount;
}
