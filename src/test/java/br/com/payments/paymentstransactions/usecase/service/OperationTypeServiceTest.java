package br.com.payments.paymentstransactions.usecase.service;

import br.com.payments.paymentstransactions.handler.MyResourceBadRequestException;
import br.com.payments.paymentstransactions.model.dto.AccountDTO;
import br.com.payments.paymentstransactions.model.dto.OperationTypeDTO;
import br.com.payments.paymentstransactions.usecase.repository.IOperationTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void should_not_save_duplicated_document_numbers() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(DataIntegrityViolationException.class);


        MyResourceBadRequestException ex = assertThrows(
                MyResourceBadRequestException.class,
                () ->  operationTypeService.save(OperationTypeDTO.builder().isDebit(false).description("descricao").build())
        );

        Assertions.assertTrue(ex.getMessage().contains("OperationType já existente"));

    }

    @Test
    void should_find_by_id() {
       operationTypeService.findById(1L);

       Mockito.verify(repository).findById(1L);
    }
}