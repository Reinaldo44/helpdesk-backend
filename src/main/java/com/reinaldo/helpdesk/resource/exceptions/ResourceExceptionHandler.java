package com.reinaldo.helpdesk.resource.exceptions;

import com.reinaldo.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.reinaldo.helpdesk.service.exceptions.ObjectnotFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler {

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());

    @ExceptionHandler(ObjectnotFoundExceptions.class)
    public ResponseEntity<StandardError> objectnotFoundException
            (ObjectnotFoundExceptions ex, HttpServletRequest request){

           StandardError error = new StandardError(date , HttpStatus.NOT_FOUND.value(),
                   "Object Not Found",ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException
            (DataIntegrityViolationException ex, HttpServletRequest request){

        StandardError error = new StandardError(date , HttpStatus.BAD_REQUEST.value(),
                "Violação de dados",ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
