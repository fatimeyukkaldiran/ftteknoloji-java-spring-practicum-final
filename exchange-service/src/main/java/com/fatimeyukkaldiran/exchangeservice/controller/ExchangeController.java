package com.fatimeyukkaldiran.exchangeservice.controller;

import com.fatimeyukkaldiran.exchangeservice.dto.request.AccountRequest;
import com.fatimeyukkaldiran.exchangeservice.dto.request.CurrencyExchangeRequest;
import com.fatimeyukkaldiran.exchangeservice.dto.response.CurrencyExchangeResponse;
import com.fatimeyukkaldiran.exchangeservice.dto.response.ExchangeRateResponse;
import com.fatimeyukkaldiran.exchangeservice.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/exchange")
public class ExchangeController {

private final ExchangeService exchangeService;

@GetMapping("/sales-purchase-info/{currency}")
public ResponseEntity<ExchangeRateResponse> getSalesAndPurchaseInfo(@PathVariable String currency){
    return new ResponseEntity<>(exchangeService.getCurrencyInfo(currency), HttpStatus.OK);
}

@PostMapping("/sales")
    public ResponseEntity<CurrencyExchangeResponse> sendSalesRequest(@Valid @RequestBody CurrencyExchangeRequest exchangeRequest){
    return new ResponseEntity<>(exchangeService.sales(exchangeRequest), HttpStatus.OK);
}

    @PostMapping("/purchase")
    public ResponseEntity<CurrencyExchangeResponse> sendPurchaseRequest(@Valid @RequestBody CurrencyExchangeRequest exchangeRequest){
        return new ResponseEntity<>(exchangeService.purchase(exchangeRequest), HttpStatus.OK);
    }

    @GetMapping("/account-last-changes")
    public ResponseEntity<AccountRequest> getAccountLastChanges(){
        return new ResponseEntity<>(exchangeService.getAccountLast(), HttpStatus.OK);
    }
}
