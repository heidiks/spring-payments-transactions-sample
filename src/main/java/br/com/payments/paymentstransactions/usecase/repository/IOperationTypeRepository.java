package br.com.payments.paymentstransactions.usecase.repository;

import br.com.payments.paymentstransactions.model.OperationType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IOperationTypeRepository extends MongoRepository<OperationType, String> {
}
