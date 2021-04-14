package br.com.payments.paymentstransactions.usecase.repository;

import br.com.payments.paymentstransactions.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends CrudRepository<Account, Long> {
}
