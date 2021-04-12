package br.com.payments.paymentstransactions.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OperationTypeDTO {

    private String description;

    @JsonProperty(value = "is_debit")
    private Boolean isDebit;

}
