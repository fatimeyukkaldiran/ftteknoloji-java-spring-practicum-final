package com.fatimeyukkaldiran.accountservice.dto.converter;

import com.fatimeyukkaldiran.accountservice.dto.account.AccountResponse;
import com.fatimeyukkaldiran.accountservice.dto.customer.CustomerDto;
import com.fatimeyukkaldiran.accountservice.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {
    public CustomerDto convertToCustomerDto(Customer from){

        return new CustomerDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getDateOfBirth(),
                from.getEmail(),
                from.getPhoneNumber(),
                from.getTurkishRepublicIdNo(),
                from.getAccounts()
                        .stream()
                        .map(a-> new AccountResponse(
                                a.getId(),
                                a.getBalance(),
                                a.getCurrency(),
                                a.getCreatedDate()
                        ))
                        .collect(Collectors.toList())
        );
    }

    public List<CustomerDto> convertToCustomerDtoList(List<Customer> customers){
        return customers
                .stream()
                .map(this::convertToCustomerDto)
                .collect(Collectors.toList());
    }
}
