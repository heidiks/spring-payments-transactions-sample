package br.com.payments.paymentstransactions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MyResourceBadRequestException extends RuntimeException {

    public MyResourceBadRequestException(String message) {
        super(message);
    }

}