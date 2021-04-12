package br.com.payments.paymentstransactions.model;


import br.com.payments.paymentstransactions.model.dto.AccountDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "account")
public class Account {

    @Id
    private String id;
    @Indexed(unique=true)
    private String documentNumber;

    public static Account of(AccountDTO dto) {
        Account account = new Account();
        account.setDocumentNumber(dto.getDocumentNumber());

        return account;
    }

}
