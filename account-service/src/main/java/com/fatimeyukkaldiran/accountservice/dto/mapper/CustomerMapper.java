package com.fatimeyukkaldiran.accountservice.dto.mapper;

import com.fatimeyukkaldiran.accountservice.dto.customer.CreateCustomerRequest;
import com.fatimeyukkaldiran.accountservice.dto.customer.CustomerDto;
import com.fatimeyukkaldiran.accountservice.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer mapToCustomer(CreateCustomerRequest request);
    Customer mapFromCustomerDtoToCustomer(CustomerDto customerDto);
    CustomerDto mapFromCustomerToCustomerDto(Customer customer);

}
