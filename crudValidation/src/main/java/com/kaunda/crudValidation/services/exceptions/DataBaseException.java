package com.kaunda.crudValidation.services.exceptions;

public class DataBaseException extends RuntimeException {
    public DataBaseException(String mensagem) {
        super(mensagem);
    }
}
