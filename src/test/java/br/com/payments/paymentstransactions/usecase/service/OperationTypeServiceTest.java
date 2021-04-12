package br.com.payments.paymentstransactions.usecase.service;

import br.com.payments.paymentstransactions.model.dto.OperationTypeDTO;
import br.com.payments.paymentstransactions.usecase.repository.IOperationTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OperationTypeServiceTest {

    @Mock
    private IOperationTypeRepository repository;

    @InjectMocks
    private OperationTypeService operationTypeService;

    @Test
    void should_save() {
        operationTypeService.save(OperationTypeDTO.builder().description("description").isDebit(true).build());

        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    void should_not_save_with_empty_dto() {
        Assertions.assertThrows(NullPointerException.class, () -> operationTypeService.save(OperationTypeDTO.builder().build()));
    }

    @Test
    void should_find_by_id() {
       operationTypeService.findById("id");

       Mockito.verify(repository).findById("id");
    }
}