package br.com.payments.paymentstransactions.controller;

import br.com.payments.paymentstransactions.model.Account;
import br.com.payments.paymentstransactions.model.dto.AccountDTO;
import br.com.payments.paymentstransactions.usecase.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account save(@RequestBody AccountDTO accountDTO) {
        return accountService.save(accountDTO);
    }

    @GetMapping
    public Optional<Account> findById(Long id) {
        return accountService.findById(id);
    }
}
