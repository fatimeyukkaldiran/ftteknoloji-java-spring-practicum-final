package com.fatimeyukkaldiran.exchangeservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchangeResponse {
    private String description;
    private String exchangedAmount;
    private String exchangeRate;

}
