package br.com.payments.paymentstransactions.usecase.service;

import br.com.payments.paymentstransactions.model.dto.AccountDTO;
import br.com.payments.paymentstransactions.model.dto.OperationTypeDTO;
import br.com.payments.paymentstransactions.usecase.repository.IAccountRepository;
import br.com.payments.paymentstransactions.usecase.repository.IOperationTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void should_find_by_id() {
        accountService.findById("id");

        Mockito.verify(repository).findById("id");
    }
}