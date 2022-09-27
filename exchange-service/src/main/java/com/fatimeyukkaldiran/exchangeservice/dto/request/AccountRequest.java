package com.fatimeyukkaldiran.exchangeservice.dto.request;

import com.fatimeyukkaldiran.exchangeservice.dto.response.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    private AccountResponse sourceAccount;
    private AccountResponse targetAccount;
    private String transactionType;
}
