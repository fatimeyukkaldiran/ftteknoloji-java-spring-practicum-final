package com.fatimeyukkaldiran.accountservice.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoDto {
    private String id;
    private BigDecimal balance;
    private String currencyName;
    private String currencySign;
}
