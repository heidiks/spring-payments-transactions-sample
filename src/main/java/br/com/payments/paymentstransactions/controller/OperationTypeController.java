package br.com.payments.paymentstransactions.controller;

import br.com.payments.paymentstransactions.model.OperationType;
import br.com.payments.paymentstransactions.model.dto.OperationTypeDTO;
import br.com.payments.paymentstransactions.usecase.service.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/operation-types")
public class OperationTypeController {

    @Autowired
    private OperationTypeService operationTypeService;

    @PostMapping
    public OperationType save(@RequestBody OperationTypeDTO dto) {
        return operationTypeService.save(dto);
    }

}
