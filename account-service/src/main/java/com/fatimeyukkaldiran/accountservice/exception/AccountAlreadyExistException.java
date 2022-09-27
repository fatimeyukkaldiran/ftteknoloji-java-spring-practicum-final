package com.fatimeyukkaldiran.accountservice.exception;

public class AccountAlreadyExistException extends RuntimeException {
    public AccountAlreadyExistException(String msg){
       super(msg);
    }

}
