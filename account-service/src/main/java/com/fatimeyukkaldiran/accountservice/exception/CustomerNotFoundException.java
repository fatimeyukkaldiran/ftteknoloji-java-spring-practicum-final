package com.fatimeyukkaldiran.accountservice.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String msg){
        super(msg);
    }
}
