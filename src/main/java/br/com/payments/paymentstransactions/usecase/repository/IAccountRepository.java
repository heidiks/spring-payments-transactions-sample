package br.com.payments.paymentstransactions.usecase.repository;

import br.com.payments.paymentstransactions.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAccountRepository extends MongoRepository<Account, String> {
}
