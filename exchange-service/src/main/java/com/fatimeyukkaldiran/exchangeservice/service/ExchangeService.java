package com.fatimeyukkaldiran.exchangeservice.service;

import com.fatimeyukkaldiran.exchangeservice.dto.request.AccountRequest;
import com.fatimeyukkaldiran.exchangeservice.dto.response.AccountResponse;
import com.fatimeyukkaldiran.exchangeservice.dto.enums.Currency;
import com.fatimeyukkaldiran.exchangeservice.dto.enums.TransactionType;
import com.fatimeyukkaldiran.exchangeservice.dto.request.CurrencyExchangeRequest;
import com.fatimeyukkaldiran.exchangeservice.dto.response.CurrencyExchangeResponse;
import com.fatimeyukkaldiran.exchangeservice.dto.response.ExchangeRateResponse;
import com.fatimeyukkaldiran.exchangeservice.dto.response.ExchangeResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.DecimalFormat;


@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final RestTemplate restTemplate;

    private final String accountUrl = "http://localhost:8080/api/v1/account";


    public ExchangeRateResponse getCurrencyInfo(String currency){
        return getExchangeRate(currency);
    }

    public CurrencyExchangeResponse sales(CurrencyExchangeRequest exchangeRequest){
        ExchangeResponse exchangeResponse =  searchAccountByCurrencyAndTRId(exchangeRequest);
        ExchangeRateResponse rateResponse = getExchangeRate(exchangeRequest.getSourceCurrency());
        BigDecimal salesRate = rateResponse.getSalesRate();

        return doSalesOrPurchase(exchangeResponse,exchangeRequest, TransactionType.SALES, salesRate);
    }

    public CurrencyExchangeResponse purchase(CurrencyExchangeRequest exchangeRequest){
        ExchangeResponse exchangeResponse =  searchAccountByCurrencyAndTRId(exchangeRequest);
        ExchangeRateResponse response = getExchangeRate(exchangeRequest.getSourceCurrency());
        BigDecimal purchaseRate = response.getPurchaseRate();

        return doSalesOrPurchase(exchangeResponse,exchangeRequest, TransactionType.PURCHASE, purchaseRate);
    }


    protected ExchangeResponse searchAccountByCurrencyAndTRId(CurrencyExchangeRequest exchangeRequest){
        return restTemplate.postForObject(accountUrl + "/is-exist-account", exchangeRequest, ExchangeResponse.class);
    }

    private CurrencyExchangeResponse doSalesOrPurchase(ExchangeResponse exchangeResponse,CurrencyExchangeRequest exchangeRequest, TransactionType transactionType, BigDecimal rate){
        AccountResponse sourceAccount = exchangeResponse.getSourceAccount();
        AccountResponse targetAccount = exchangeResponse.getTargetAccount();

        if (transactionType.equals(TransactionType.SALES)){
            BigDecimal soldAmount = exchangeRequest.getAmountToConvert().multiply(rate);
            sourceAccount.setBalance(sourceAccount.getBalance().add(exchangeRequest.getAmountToConvert()));
            targetAccount.setBalance(targetAccount.getBalance().subtract(soldAmount));

            saveAccountLastChanged(sourceAccount,targetAccount,TransactionType.SALES.toString());
        }
        if (transactionType.equals(TransactionType.PURCHASE)){
            BigDecimal purchasedAmount = exchangeRequest.getAmountToConvert().multiply(rate);
            sourceAccount.setBalance(sourceAccount.getBalance().subtract(exchangeRequest.getAmountToConvert()));
            targetAccount.setBalance(targetAccount.getBalance().add(purchasedAmount));

            saveAccountLastChanged(sourceAccount,targetAccount,TransactionType.PURCHASE.toString());

        }
        return getResponse(rate,exchangeRequest.getAmountToConvert(),exchangeRequest.getSourceCurrency(),transactionType.toString());
    }

    private CurrencyExchangeResponse getResponse(BigDecimal rate, BigDecimal amount, String currencySign, String transactionType){
        DecimalFormat formatter = new DecimalFormat("#0.0000");

        CurrencyExchangeResponse response = new CurrencyExchangeResponse();

        response.setExchangeRate(formatter.format(rate));
        response.setExchangedAmount(formatter.format(amount) + currencySign);
        response.setDescription(transactionType);

        return response;
    }

    private ExchangeRateResponse getExchangeRate(String currency){
        ExchangeRateResponse response = new ExchangeRateResponse();

        BigDecimal purchaseRate = BigDecimal.ZERO;
        BigDecimal salesRate = BigDecimal.ZERO;
        String currencyName = "";

        if(currency.equalsIgnoreCase(String.valueOf(Currency.EUR))){
            purchaseRate = calculateRate("17.0000","17.6515");
            salesRate    = calculateRate("18.0000","18.5415");
            currencyName = String.valueOf(Currency.EUR);
        }if (currency.equalsIgnoreCase(String.valueOf(Currency.USD))){
            purchaseRate = calculateRate("17.1256","17.9889");
            salesRate    = calculateRate("18.5643","18.9895");
            currencyName = String.valueOf(Currency.USD);
        }if (currency.equalsIgnoreCase(String.valueOf(Currency.ALTIN))){
            purchaseRate = calculateRate("916.85","985.86");
            salesRate    = calculateRate("1.0065","1.0095");
            currencyName = String.valueOf(Currency.ALTIN);
        }

        response.setPurchaseRate(purchaseRate);
        response.setSalesRate(salesRate);
        response.setCurrencyName(currencyName);

        return response;
    }

    private BigDecimal calculateRate(String minN, String maxN) {
        BigDecimal max = new BigDecimal(maxN);
        BigDecimal min = new BigDecimal(minN);
        BigDecimal range = max.subtract(min);

        return min.add(range.multiply(BigDecimal.valueOf(Math.random())));
    }

    private void saveAccountLastChanged(AccountResponse source, AccountResponse target, String transactionType) {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setSourceAccount(source);
        accountRequest.setTargetAccount(target);
        accountRequest.setTransactionType(transactionType);
    }
}
