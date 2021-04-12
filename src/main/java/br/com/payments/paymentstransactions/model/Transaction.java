package br.com.payments.paymentstransactions.model;

import br.com.payments.paymentstransactions.model.dto.TransactionDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Document(collection = "transaction")
public class Transaction {

    @Id
    private String id;
    private String accountId;
    private String operationTypeId;
    private Double amount;
    private LocalDateTime eventDate;

    public static Transaction of(TransactionDTO dto) {
        Transaction transactionToSave = Transaction.builder()
                .accountId(dto.getAccountID())
                .operationTypeId(dto.getOperationTypeId())
                .amount(dto.getAmount())
                .eventDate(LocalDateTime.now()).build();

        return transactionToSave;
    }
}
