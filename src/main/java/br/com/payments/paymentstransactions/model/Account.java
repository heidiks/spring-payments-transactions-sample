package br.com.payments.paymentstransactions.model;


import br.com.payments.paymentstransactions.model.dto.AccountDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String documentNumber;

    public static Account of(AccountDTO dto) {
        Account account = new Account();
        account.setDocumentNumber(dto.getDocumentNumber());

        return account;
    }

}
