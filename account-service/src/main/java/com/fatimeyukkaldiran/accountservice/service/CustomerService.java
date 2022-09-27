package com.fatimeyukkaldiran.accountservice.service;

import com.fatimeyukkaldiran.accountservice.dto.converter.CustomerConverter;
import com.fatimeyukkaldiran.accountservice.dto.customer.CreateCustomerRequest;
import com.fatimeyukkaldiran.accountservice.dto.customer.CustomerDto;
import com.fatimeyukkaldiran.accountservice.dto.customer.UpdateCustomerRequest;
import com.fatimeyukkaldiran.accountservice.dto.mapper.CustomerMapper;
import com.fatimeyukkaldiran.accountservice.entity.Customer;
import com.fatimeyukkaldiran.accountservice.exception.BadRequestException;
import com.fatimeyukkaldiran.accountservice.exception.CustomerNotFoundException;
import com.fatimeyukkaldiran.accountservice.exception.ErrorMessageConstants;
import com.fatimeyukkaldiran.accountservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;
  private final CustomerConverter customerConverter;

  public Optional<CustomerDto> createCustomer(CreateCustomerRequest createRequest){
      boolean isExist = customerRepository.selectExistsTurkishRepublicIdNo(createRequest.getTurkishRepublicIdNo());
      if (isExist){
          throw new BadRequestException(ErrorMessageConstants.CUSTOMER_EXIST);
      }
      Customer customer = customerRepository.save(customerMapper.mapToCustomer(createRequest));
      return Optional.of(customerMapper.mapFromCustomerToCustomerDto(customer));
  }

  public List<CustomerDto> getAllCustomers(){
      return customerConverter.convertToCustomerDtoList(customerRepository.findAll());
  }

    public CustomerDto getCustomerById(String turkishIdNo){
        return customerMapper.mapFromCustomerToCustomerDto(findCustomer(turkishIdNo));
    }


    public CustomerDto updateCustomer(UpdateCustomerRequest customerUpdateRequest){
        Customer customer = findCustomer(customerUpdateRequest.getTurkishRepublicIdNo());

        Customer updatedCustomer = new Customer(
                customer.getId(),
                customerUpdateRequest.getFirstName(),
                customerUpdateRequest.getLastName(),
                customer.getDateOfBirth(),
                customerUpdateRequest.getEmail(),
                customerUpdateRequest.getPhoneNumber(),
                customer.getTurkishRepublicIdNo(),
                customer.getAccounts()
        );
        return customerConverter.convertToCustomerDto(customerRepository.save(updatedCustomer));
    }

    public String deleteCustomer(String turkishIdNo){
        customerRepository.delete(findCustomer(turkishIdNo));
        return turkishIdNo + " deleted ...";
    }

    public Customer findCustomer(String turkishIdNo){
        return customerRepository.findCustomerByTurkishRepublicIdNo(turkishIdNo)
                .orElseThrow(() -> new CustomerNotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND));
    }
}
