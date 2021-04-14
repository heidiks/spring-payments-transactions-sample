package br.com.payments.paymentstransactions.usecase.repository;

import br.com.payments.paymentstransactions.model.OperationType;
import org.springframework.data.repository.CrudRepository;

public interface IOperationTypeRepository extends CrudRepository<OperationType, Long> {
}
