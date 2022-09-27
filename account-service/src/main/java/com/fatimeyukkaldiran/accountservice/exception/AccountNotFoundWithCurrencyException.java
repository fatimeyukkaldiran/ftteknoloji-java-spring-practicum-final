package com.fatimeyukkaldiran.accountservice.exception;

public class AccountNotFoundWithCurrencyException extends RuntimeException{
    public AccountNotFoundWithCurrencyException(String msg){
        super(msg);
    }
}
