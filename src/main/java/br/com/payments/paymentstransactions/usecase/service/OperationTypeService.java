package br.com.payments.paymentstransactions.usecase.service;

import br.com.payments.paymentstransactions.handler.MyResourceBadRequestException;
import br.com.payments.paymentstransactions.model.OperationType;
import br.com.payments.paymentstransactions.model.dto.OperationTypeDTO;
import br.com.payments.paymentstransactions.usecase.repository.IOperationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OperationTypeService {

    @Autowired
    private IOperationTypeRepository repository;

    public Optional<OperationType> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public OperationType save(OperationTypeDTO dto) {
        OperationType operationType = OperationType.of(dto);

        try {
            return repository.save(operationType);
        } catch (DataIntegrityViolationException de) {
            throw  new MyResourceBadRequestException("OperationType já existente");
        }
    }
}
