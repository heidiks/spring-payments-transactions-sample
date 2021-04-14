package br.com.payments.paymentstransactions.model;


import br.com.payments.paymentstransactions.model.dto.OperationTypeDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String description;

    @Column(nullable = false)
    private boolean isDebit;

    public static OperationType of(OperationTypeDTO dto) {
        return OperationType.builder().description(dto.getDescription()).isDebit(dto.getIsDebit()).build();
    }

}
