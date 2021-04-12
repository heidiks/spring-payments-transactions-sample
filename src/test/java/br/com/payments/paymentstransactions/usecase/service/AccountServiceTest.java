package br.com.payments.paymentstransactions.usecase.service;

import br.com.payments.paymentstransactions.handler.MyResourceBadRequestException;
import br.com.payments.paymentstransactions.model.dto.AccountDTO;
import br.com.payments.paymentstransactions.usecase.repository.IAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private IAccountRepository repository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void should_save() {
        accountService.save(AccountDTO.builder().documentNumber("document_number").build());

        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    void should_not_save_duplicated_document_numbers() {
        Mockito.when(repository.save(Mockito.any())).thenThrow(DuplicateKeyException.class);


        MyResourceBadRequestException ex = assertThrows(
                MyResourceBadRequestException.class,
                () ->  accountService.save(AccountDTO.builder().documentNumber("document_number").build())
        );

        Assertions.assertTrue(ex.getMessage().contains("Account já existente"));

    }

    @Test
    void should_find_by_id() {
        accountService.findById("id");

        Mockito.verify(repository).findById("id");
    }

}