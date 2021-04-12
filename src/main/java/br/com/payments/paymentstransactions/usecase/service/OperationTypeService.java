package br.com.payments.paymentstransactions.usecase.service;

import br.com.payments.paymentstransactions.model.OperationType;
import br.com.payments.paymentstransactions.model.dto.OperationTypeDTO;
import br.com.payments.paymentstransactions.usecase.repository.IOperationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationTypeService {

    @Autowired
    private IOperationTypeRepository repository;

    public Optional<OperationType> findById(String id) {
        return repository.findById(id);
    }

    public OperationType save(OperationTypeDTO dto) {
        OperationType operationType = OperationType.of(dto);
        return repository.save(operationType);
    }
}
