package br.com.payments.paymentstransactions.model.dto;

import br.com.payments.paymentstransactions.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class AccountDTO implements Serializable {

    private String id;
    @JsonProperty(value = "document_number")
    private String documentNumber;

    public static AccountDTO of(Account account) {
        return AccountDTO.builder().id(account.getId()).documentNumber(account.getDocumentNumber()).build();
    }
}
