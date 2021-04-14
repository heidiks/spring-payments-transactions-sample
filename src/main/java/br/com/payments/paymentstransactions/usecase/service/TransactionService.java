package br.com.payments.paymentstransactions.usecase.service;

import br.com.payments.paymentstransactions.handler.MyResourceBadRequestException;
import br.com.payments.paymentstransactions.handler.MyResourceNotFoundException;
import br.com.payments.paymentstransactions.model.Account;
import br.com.payments.paymentstransactions.model.OperationType;
import br.com.payments.paymentstransactions.model.Transaction;
import br.com.payments.paymentstransactions.model.dto.TransactionDTO;
import br.com.payments.paymentstransactions.usecase.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private ITransactionRepository repository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OperationTypeService operationTypeService;

    @Transactional
    public Transaction doTransaction(TransactionDTO dto) {

        Optional<Account> account = accountService.findById(dto.getAccountID());
        if(!account.isPresent())
            throw new MyResourceNotFoundException("Account não existente");
        Optional<OperationType> operationType = operationTypeService.findById(dto.getOperationTypeId());
        if(!operationType.isPresent())
            throw new MyResourceNotFoundException("Operation Type não existente");

        Transaction transaction = Transaction.of(dto);
        if(operationType.get().isDebit()) {
            checkCreditLimit(account, transaction);
            transaction.setAmount(dto.getAmount() * -1);
        }

        double newLimit = account.get().getAvailableCreditLimit() + transaction.getAmount();
        account.get().setAvailableCreditLimit(newLimit);
        accountService.update(account.get());

        return repository.save(transaction);
    }

    private void checkCreditLimit(Optional<Account> account, Transaction transaction) {
        if(transaction.getAmount() > account.get().getAvailableCreditLimit())
            throw new MyResourceBadRequestException("Valor da transação maior que limite disponível");
    }

}
