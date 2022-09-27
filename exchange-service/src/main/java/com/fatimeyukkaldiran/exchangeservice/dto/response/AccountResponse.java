package com.fatimeyukkaldiran.exchangeservice.dto.response;


import com.fatimeyukkaldiran.exchangeservice.dto.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountResponse {
    private String id;
    private BigDecimal balance;
    private Currency currency;
    private LocalDateTime createdDate;
    private String turkishId;

}
