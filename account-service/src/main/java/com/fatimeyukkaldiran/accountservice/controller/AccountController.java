package com.fatimeyukkaldiran.accountservice.controller;

import com.fatimeyukkaldiran.accountservice.dto.exchange.CurrencyExchangeRequest;
import com.fatimeyukkaldiran.accountservice.dto.exchange.ExchangeResponse;
import com.fatimeyukkaldiran.accountservice.dto.account.AccountDto;
import com.fatimeyukkaldiran.accountservice.dto.account.AccountInfoDto;
import com.fatimeyukkaldiran.accountservice.dto.account.CreateAccountRequest;
import com.fatimeyukkaldiran.accountservice.entity.AccountServiceTransactionLogger;
import com.fatimeyukkaldiran.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> save(@Valid @RequestBody CreateAccountRequest accountCreateRequest){
        Optional<AccountDto> accountDto = accountService.createAccount(accountCreateRequest);
        return new ResponseEntity<>(accountDto.orElseThrow(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<AccountInfoDto>> getAccountsByCurrency(){
        List<AccountInfoDto> accounts = accountService.getAccountInfoWithCurrency();

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{customerTRId}")
    public ResponseEntity<List<AccountDto>> getAll(@PathVariable String customerTRId){
        List<AccountDto> accounts = accountService.getAccounts(customerTRId);

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/detail/{customerTRId}/{currency}")
    public ResponseEntity<AccountInfoDto> getAccountDetail(@PathVariable String customerTRId, @PathVariable String currency){
        AccountInfoDto account = accountService.getAccountInfo(customerTRId, currency);

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/get-transactions")
    public ResponseEntity<Page<AccountServiceTransactionLogger>> getAllTransactions(@PageableDefault(size = 5)Pageable pageable){

        return new ResponseEntity<>(accountService.getAccountActivities(pageable), HttpStatus.OK);
    }

    @PostMapping("is-exist-account")
    public ResponseEntity<ExchangeResponse> searchAccount(@Valid @RequestBody CurrencyExchangeRequest exchangeRequest){
        ExchangeResponse response = accountService.searchAccount(exchangeRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
