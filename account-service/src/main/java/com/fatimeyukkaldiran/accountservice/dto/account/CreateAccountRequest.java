package com.fatimeyukkaldiran.accountservice.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatimeyukkaldiran.accountservice.entity.enums.Currency;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 11, fraction = 2)
    private BigDecimal balance;

    @JsonIgnore
    private Currency currency;

    private String turkishRepublicIdNo;
}
