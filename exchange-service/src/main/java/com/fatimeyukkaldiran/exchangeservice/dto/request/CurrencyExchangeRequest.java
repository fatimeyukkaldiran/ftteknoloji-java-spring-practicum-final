package com.fatimeyukkaldiran.exchangeservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchangeRequest {
    @NotBlank
    @Size(max = 3, min = 3)
    private String sourceCurrency;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 11, fraction = 2)
    private BigDecimal amountToConvert;

    @NotBlank
    @Size(max = 3, min = 3)
    private String targetCurrency;

    @Size(max =11)
    private String turkishRepublicIdNo;
}
