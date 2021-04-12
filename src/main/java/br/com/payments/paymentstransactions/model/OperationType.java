package br.com.payments.paymentstransactions.model;


import br.com.payments.paymentstransactions.model.dto.OperationTypeDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OperationType")
@Getter
@Setter
@Builder
public class OperationType {

    @Id
    private String id;
    private String description;
    private boolean isDebit;

    public static OperationType of(OperationTypeDTO dto) {
        return OperationType.builder().description(dto.getDescription()).isDebit(dto.getIsDebit()).build();
    }

}
