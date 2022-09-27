package com.fatimeyukkaldiran.accountservice.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fatimeyukkaldiran.accountservice.entity.Customer;
import com.fatimeyukkaldiran.accountservice.entity.enums.Currency;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountDto {
    private String id;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private LocalDateTime createdDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Customer customer;
}
