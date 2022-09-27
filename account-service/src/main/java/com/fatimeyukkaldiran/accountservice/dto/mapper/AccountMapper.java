package com.fatimeyukkaldiran.accountservice.dto.mapper;

import com.fatimeyukkaldiran.accountservice.dto.account.AccountDto;
import com.fatimeyukkaldiran.accountservice.dto.account.CreateAccountRequest;
import com.fatimeyukkaldiran.accountservice.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account mapFromRequestToAccount(CreateAccountRequest request);
    Account mapFromAccountDtoToAccount(AccountDto accountDto);
    AccountDto mapFromAccountToAccountDto(Account account);
    List<AccountDto> mapFromAccountListToAccountDtoList(List<Account> accounts);
}
