package br.com.payments.paymentstransactions.controller;

import br.com.payments.paymentstransactions.model.Transaction;
import br.com.payments.paymentstransactions.model.dto.TransactionDTO;
import br.com.payments.paymentstransactions.usecase.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction save(@RequestBody TransactionDTO dto) {
        return transactionService.save(dto);
    }
}
