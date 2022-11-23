package com.reinaldo.helpdesk.service.exceptions;

public class ObjectnotFoundExceptions extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ObjectnotFoundExceptions(String message) {
        super(message);
    }

    public ObjectnotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
