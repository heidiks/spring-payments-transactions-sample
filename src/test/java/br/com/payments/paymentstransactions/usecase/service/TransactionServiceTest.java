package br.com.payments.paymentstransactions.usecase.service;

import br.com.payments.paymentstransactions.handler.MyResourceBadRequestException;
import br.com.payments.paymentstransactions.handler.MyResourceNotFoundException;
import br.com.payments.paymentstransactions.model.Account;
import br.com.payments.paymentstransactions.model.OperationType;
import br.com.payments.paymentstransactions.model.Transaction;
import br.com.payments.paymentstransactions.model.dto.AccountDTO;
import br.com.payments.paymentstransactions.model.dto.TransactionDTO;
import br.com.payments.paymentstransactions.usecase.repository.ITransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private ITransactionRepository repository;
    @Mock
    private AccountService accountService;
    @Mock
    private OperationTypeService operationTypeService;

    @InjectMocks
    private TransactionService transactionService;

    private TransactionDTO dto;
    private AccountDTO accountDTO;


    @BeforeEach
    private void setup() {
        dto = new TransactionDTO();
        dto.setAmount(123.0);
        dto.setAccountID(1L);
        dto.setOperationTypeId(1L);

        accountDTO = new AccountDTO();
        accountDTO.setDocumentNumber("document_number");
    }

    @Test
    void should_throw_exception_account_not_found() {
        when(accountService.findById(Mockito.any())).thenReturn(Optional.empty());

        MyResourceNotFoundException myResourceNotFoundException = Assertions.assertThrows(
                MyResourceNotFoundException.class,
                () -> transactionService.doTransaction(dto)
        );

        Assertions.assertTrue(myResourceNotFoundException.getMessage().contains("Account não existente"));

    }

    @Test
    void should_throw_exception_operation_type_not_found() {
        when(accountService.findById(Mockito.any())).thenReturn(Optional.of(new Account()));
        when(operationTypeService.findById(Mockito.any())).thenReturn(Optional.empty());

        MyResourceNotFoundException myResourceNotFoundException = Assertions.assertThrows(
                MyResourceNotFoundException.class,
                () -> transactionService.doTransaction(dto)
        );

        Assertions.assertTrue(myResourceNotFoundException.getMessage().contains("Operation Type não existente"));
    }

    @Test
    void should_throw_exception_with_negative_amount_value() {
        when(accountService.findById(Mockito.any())).thenReturn(Optional.of(new Account()));

        OperationType operationType = OperationType.builder().isDebit(false).description("PAGAMENTO").build();
        when(operationTypeService.findById(Mockito.any())).thenReturn(Optional.of(operationType));
        dto.setAmount(-123.0);

        MyResourceBadRequestException myResourceNotFoundException = Assertions.assertThrows(
                MyResourceBadRequestException.class,
                () -> transactionService.doTransaction(dto)
        );

        Assertions.assertTrue(myResourceNotFoundException.getMessage().contains("Campo Amount não deve ser negativo"));

    }

    @Test
    void should_not_save_if_doesnt_have_credit_limit() {
        accountDTO.setAvailableCreditLimit(10.0);
        Account account = Account.of(accountDTO);

        when(accountService.findById(Mockito.any())).thenReturn(Optional.of(account));
        OperationType operationType = OperationType.builder().isDebit(true).description("PAGAMENTO").build();
        when(operationTypeService.findById(Mockito.any())).thenReturn(Optional.of(operationType));

        MyResourceBadRequestException ex = Assertions.assertThrows(
                MyResourceBadRequestException.class,
                () -> transactionService.doTransaction(dto)
        );

        Assertions.assertTrue(ex.getMessage().contains("Valor da transação maior que limite disponível"));

    }

    @Test
    void should_save_credit_transaction() {
        Account account = Account.of(accountDTO);
        when(accountService.findById(Mockito.any())).thenReturn(Optional.of(account));

        OperationType operationType = OperationType.builder().isDebit(false).description("PAGAMENTO").build();
        when(operationTypeService.findById(Mockito.any())).thenReturn(Optional.of(operationType));

        when(repository.save(Mockito.any())).thenReturn(Mockito.any(Transaction.class));

        transactionService.doTransaction(dto);

        Mockito.verify(repository).save(Mockito.any());
    }

    @Test
    void should_save_debit_transaction() {
        Account account = Account.of(accountDTO);
        when(accountService.findById(Mockito.any())).thenReturn(Optional.of(account));

        OperationType operationType = OperationType.builder().isDebit(true).description("SAQUE").build();
        when(operationTypeService.findById(Mockito.any())).thenReturn(Optional.of(operationType));

        when(repository.save(Mockito.any())).thenReturn(Mockito.any(Transaction.class));

        transactionService.doTransaction(dto);

        Mockito.verify(repository).save(Mockito.any());
    }
}