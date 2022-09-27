package com.fatimeyukkaldiran.accountservice.dto.converter;

import com.fatimeyukkaldiran.accountservice.dto.exchange.AccountResponse;
import com.fatimeyukkaldiran.accountservice.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public AccountResponse convertToAccountResponse(Account account){
        return new AccountResponse(
                account.getId(),
                account.getBalance(),
                account.getCurrency(),
                account.getCreatedDate(),
                account.getCustomer().getTurkishRepublicIdNo()
        );
    }
}
