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

    public static final double DEFAULT_AVAILABLE_CREDIT_LIMIT = 500.0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String documentNumber;

    @Column
    private Double availableCreditLimit;

    public static Account of(AccountDTO dto) {
        Account account = new Account();
        account.setDocumentNumber(dto.getDocumentNumber());
        account.setAvailableCreditLimit(dto.getAvailableCreditLimit());
        if(dto.getAvailableCreditLimit() == null)
            account.setAvailableCreditLimit(DEFAULT_AVAILABLE_CREDIT_LIMIT);

        return account;
    }

}
