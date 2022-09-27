package com.fatimeyukkaldiran.accountservice.repository;

import com.fatimeyukkaldiran.accountservice.entity.Account;
import com.fatimeyukkaldiran.accountservice.entity.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("SELECT CASE WHEN COUNT(a)>0 THEN TRUE ELSE FALSE END FROM Account a WHERE a.currency=?1 and a.customer.turkishRepublicIdNo =?2 ")
    boolean selectExistAccountSameCurrency(Currency currency, String id);

    Account findAccountByCustomer_TurkishRepublicIdNoAndCurrencyEquals(String turkishId, String currency);

    Account findAccountByCurrency_CurrencyName(String currency);

    List<Account> findAccountByCustomer_TurkishRepublicIdNo(String turkishId);
}
