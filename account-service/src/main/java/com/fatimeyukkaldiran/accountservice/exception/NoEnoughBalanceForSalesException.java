package com.fatimeyukkaldiran.accountservice.exception;

public class NoEnoughBalanceForSalesException extends RuntimeException{
    public NoEnoughBalanceForSalesException(String msg){
        super(msg);
    }
}
