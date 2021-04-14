package br.com.payments.paymentstransactions.model;

import br.com.payments.paymentstransactions.handler.MyResourceBadRequestException;
import br.com.payments.paymentstransactions.model.dto.TransactionDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long accountId;
    private Long operationTypeId;
    private Double amount;
    private LocalDateTime eventDate;

    public static Transaction of(TransactionDTO dto) {
        if(dto.getAmount() < 0)
            throw new MyResourceBadRequestException("Campo Amount não deve ser negativo");

        Transaction transactionToSave = Transaction.builder()
                .accountId(dto.getAccountID())
                .operationTypeId(dto.getOperationTypeId())
                .amount(dto.getAmount())
                .eventDate(LocalDateTime.now()).build();

        return transactionToSave;
    }
}
