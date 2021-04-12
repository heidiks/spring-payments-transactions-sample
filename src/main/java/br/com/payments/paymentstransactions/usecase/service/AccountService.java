package br.com.payments.paymentstransactions.usecase.service;

import br.com.payments.paymentstransactions.handler.MyResourceBadRequestException;
import br.com.payments.paymentstransactions.model.Account;
import br.com.payments.paymentstransactions.model.dto.AccountDTO;
import br.com.payments.paymentstransactions.usecase.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private IAccountRepository IAccountRepository;

    public Account save(AccountDTO accountDTO) {
        Account account = Account.of(accountDTO);

        try {
            return IAccountRepository.save(account);
        } catch (DuplicateKeyException de) {
            throw  new MyResourceBadRequestException("Account já existente");
        }

    }

    public Optional<Account> findById(String id) {
        return IAccountRepository.findById(id);
    }
}
