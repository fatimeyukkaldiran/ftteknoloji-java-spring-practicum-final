package com.fatimeyukkaldiran.accountservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handle(CustomerNotFoundException exception){
        return handleNotFound(exception.getMessage());
    }

    @ExceptionHandler(AccountAlreadyExistException.class)
    public ResponseEntity<?> handle(AccountAlreadyExistException exception){
        return handleNotFound(exception.getMessage());
    }

    @ExceptionHandler(AccountNotFoundWithCurrencyException.class)
    public ResponseEntity<?> handle(AccountNotFoundWithCurrencyException exception){
        return handleNotFound(exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handle(BadRequestException exception){
        return handleNotFound(exception.getMessage());
    }

    @ExceptionHandler(NoEnoughBalanceForSalesException.class)
    public ResponseEntity<?> handle(NoEnoughBalanceForSalesException exception){
        return handleNotFound(exception.getMessage());
    }

    private ResponseEntity<?> handleNotFound(String message) {
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}
