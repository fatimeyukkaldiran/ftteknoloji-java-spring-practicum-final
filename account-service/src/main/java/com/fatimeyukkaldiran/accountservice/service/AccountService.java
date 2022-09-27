package com.fatimeyukkaldiran.accountservice.service;

import com.fatimeyukkaldiran.accountservice.dto.converter.AccountConverter;
import com.fatimeyukkaldiran.accountservice.dto.exchange.CurrencyExchangeRequest;
import com.fatimeyukkaldiran.accountservice.dto.exchange.ExchangeResponse;
import com.fatimeyukkaldiran.accountservice.dto.account.*;
import com.fatimeyukkaldiran.accountservice.dto.mapper.AccountMapper;
import com.fatimeyukkaldiran.accountservice.entity.Account;
import com.fatimeyukkaldiran.accountservice.entity.AccountServiceTransactionLogger;
import com.fatimeyukkaldiran.accountservice.entity.Customer;
import com.fatimeyukkaldiran.accountservice.entity.enums.Currency;
import com.fatimeyukkaldiran.accountservice.entity.enums.TransactionType;
import com.fatimeyukkaldiran.accountservice.exception.*;
import com.fatimeyukkaldiran.accountservice.repository.AccountRepository;
import com.fatimeyukkaldiran.accountservice.repository.TransactionLoggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerService customerService;
    private final TransactionLoggerRepository transactionLoggerRepository;
    private final AccountConverter accountConverter;


    public Optional<AccountDto> createAccount(CreateAccountRequest accountRequest){
        Customer customer = customerService.findCustomer(accountRequest.getTurkishRepublicIdNo());

        Account account = accountMapper.mapFromRequestToAccount(accountRequest);
        boolean isExistAccount = accountRepository.selectExistAccountSameCurrency(account.getCurrency(), customer.getTurkishRepublicIdNo());
        if (isExistAccount){
            throw new AccountAlreadyExistException(ErrorMessageConstants.ACCOUNT_ALREADY_EXIST);
        }

        AccountDto accountDto = accountMapper.mapFromAccountToAccountDto(accountRepository.save(account));
        return Optional.of(accountDto);
    }

    public List<AccountDto> getAccounts(String turkishId) {
        List<Account> accounts = accountRepository.findAccountByCustomer_TurkishRepublicIdNo(turkishId);
        return accountMapper.mapFromAccountListToAccountDtoList(accounts);
    }

    public AccountInfoDto getAccountInfo(String customerTRId, String currency) {
        Customer customer = customerService.findCustomer(customerTRId);
        Account account = accountRepository.findAccountByCustomer_TurkishRepublicIdNoAndCurrencyEquals(customer.getTurkishRepublicIdNo(),currency);
        AccountInfoDto accountInfoDto = new AccountInfoDto();

        accountInfoDto.setId(account.getId());
        accountInfoDto.setBalance(account.getBalance());
        accountInfoDto.setCurrencyName(account.getCurrency().getCurrencyName());
        accountInfoDto.setCurrencySign(account.getCurrency().getCurrencySign());

        return accountInfoDto;
    }

    public Page<AccountServiceTransactionLogger> getAccountActivities(Pageable pageable){
    pageable = (PageRequest.of(0,5, Sort.by("transaction_date").descending()));
      return this.transactionLoggerRepository.findAllByTransaction_date(pageable);
    }

    public List<AccountInfoDto> getAccountInfoWithCurrency() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountInfoDto> accountInfoDtoList = new ArrayList<>();
        AccountInfoDto accountInfoDto = new AccountInfoDto();

            accounts.forEach(a -> {
                        accountInfoDto.setId(a.getId());
                        accountInfoDto.setCurrencyName(a.getCurrency().getCurrencyName());
                        accountInfoDto.setCurrencySign(a.getCurrency().getCurrencySign());
                        accountInfoDto.setBalance(a.getBalance());
                        accountInfoDtoList.add(accountInfoDto);
            });
         return accountInfoDtoList;
    }


    public ExchangeResponse searchAccount(CurrencyExchangeRequest exchangeRequest) {
        ExchangeResponse response = new ExchangeResponse();
        Account sourceAccount = isCurrencyExistAndBalanceEnough(exchangeRequest.getTurkishRepublicIdNo(), exchangeRequest.getSourceCurrency(), exchangeRequest.getAmountToConvert());
        Account targetAccount = getAccountByCustomerTurkishIdAndCurrency(exchangeRequest.getTurkishRepublicIdNo(), exchangeRequest.getTargetCurrency());

        response.setSourceAccount(accountConverter.convertToAccountResponse(sourceAccount));
        response.setTargetAccount(accountConverter.convertToAccountResponse(targetAccount));
        return response;
    }

    private Account isCurrencyExistAndBalanceEnough(String turkishId, String currency, BigDecimal quantity) {
        Account account = getAccountByCustomerTurkishIdAndCurrency(turkishId, currency);
        if (account.getBalance().compareTo(quantity) < 0){
            throw new NoEnoughBalanceForSalesException(ErrorMessageConstants.BALANCE_IS_NOT_ENOUGH);
        }
        return account;
    }

    private Account getAccountByCustomerTurkishIdAndCurrency(String turkishId, String currency){
        Customer foundCustomer = customerService.findCustomer(turkishId);
        Optional<Account> account = foundCustomer.getAccounts().stream().filter(a -> a.getCurrency().equals(Currency.valueOf(currency.toUpperCase()))).findFirst();

       return account.
                orElseThrow(() -> new AccountNotFoundWithCurrencyException(ErrorMessageConstants.ACCOUNT_NOT_EXIST_WITH_CURRENCY));
    }

    private void saveTransactionToDatabase(Account account, BigDecimal amount, TransactionType type ){
        AccountServiceTransactionLogger transactionLogger = new AccountServiceTransactionLogger();
        transactionLogger.setCustomerId(account.getCustomer().getTurkishRepublicIdNo());
        transactionLogger.setBalance_before(account.getBalance());
        transactionLogger.setBalance_after(account.getBalance());
        transactionLogger.setTransaction_amount(amount);
        transactionLogger.setTransaction_currency(account.getCurrency());
        transactionLogger.setTransaction_date(LocalDateTime.now());
        transactionLogger.setTransactionType(type);
        this.transactionLoggerRepository.save(transactionLogger);
    }

}
