package br.com.payments.paymentstransactions.usecase.repository;

import br.com.payments.paymentstransactions.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface ITransactionRepository extends CrudRepository<Transaction, Long> {
}
