package com.fatimeyukkaldiran.accountservice.dto.exchange;


import com.fatimeyukkaldiran.accountservice.entity.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private String id;
    private BigDecimal balance;
    private Currency currency;
    private LocalDateTime createdDate;
    private String turkishId;
}
