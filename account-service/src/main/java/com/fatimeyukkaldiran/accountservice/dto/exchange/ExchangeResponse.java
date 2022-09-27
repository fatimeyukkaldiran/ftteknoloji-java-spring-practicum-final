package com.fatimeyukkaldiran.accountservice.dto.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeResponse {
    AccountResponse sourceAccount;
    AccountResponse targetAccount;
}
