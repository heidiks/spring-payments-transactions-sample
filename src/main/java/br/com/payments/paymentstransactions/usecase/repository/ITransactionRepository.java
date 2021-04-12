package br.com.payments.paymentstransactions.usecase.repository;

import br.com.payments.paymentstransactions.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITransactionRepository extends MongoRepository<Transaction, String> {
}
