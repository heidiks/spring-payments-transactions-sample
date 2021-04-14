package br.com.payments.paymentstransactions.model.dto;

import br.com.payments.paymentstransactions.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO implements Serializable {

    private Long id;
    @JsonProperty(value = "document_number")
    private String documentNumber;
    @JsonProperty(value = "available_credit_limit")
    private Double availableCreditLimit;

    public static AccountDTO of(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .documentNumber(account.getDocumentNumber())
                .availableCreditLimit(account.getAvailableCreditLimit())
                .build();
    }
}
