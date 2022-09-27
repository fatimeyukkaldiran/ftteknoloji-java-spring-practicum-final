package com.fatimeyukkaldiran.exchangeservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateResponse {

    private BigDecimal purchaseRate;
    private BigDecimal salesRate;
    private String currencyName;
}
