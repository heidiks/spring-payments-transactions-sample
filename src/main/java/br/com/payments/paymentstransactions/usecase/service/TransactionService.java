package br.com.payments.paymentstransactions.usecase.service;

import br.com.payments.paymentstransactions.handler.MyResourceNotFoundException;
import br.com.payments.paymentstransactions.model.Account;
import br.com.payments.paymentstransactions.model.OperationType;
import br.com.payments.paymentstransactions.model.Transaction;
import br.com.payments.paymentstransactions.model.dto.TransactionDTO;
import br.com.payments.paymentstransactions.usecase.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private ITransactionRepository repository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OperationTypeService operationTypeService;

    public Transaction save(TransactionDTO dto) {

        Optional<Account> account = accountService.findById(dto.getAccountID());
        if(!account.isPresent())
            throw new MyResourceNotFoundException("Account não existente");
        Optional<OperationType> operationType = operationTypeService.findById(dto.getOperationTypeId());
        if(!operationType.isPresent())
            throw new MyResourceNotFoundException("Operation Type não existente");

        Transaction transaction = Transaction.of(dto);
        if(operationType.get().isDebit())
            transaction.setAmount(dto.getAmount() * -1);

        return repository.save(transaction);
    }

}
