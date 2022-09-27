package com.fatimeyukkaldiran.accountservice.controller;

import com.fatimeyukkaldiran.accountservice.dto.customer.CreateCustomerRequest;
import com.fatimeyukkaldiran.accountservice.dto.customer.CustomerDto;
import com.fatimeyukkaldiran.accountservice.dto.customer.UpdateCustomerRequest;
import com.fatimeyukkaldiran.accountservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> save(@Valid @RequestBody CreateCustomerRequest customerCreateRequest){
        Optional<CustomerDto> customerDto = customerService.createCustomer(customerCreateRequest);
        return new ResponseEntity<>(customerDto.orElseThrow(), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAll(){
        List<CustomerDto> customerDtoList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @GetMapping(path="/{turkishIdNo}")
    public ResponseEntity<CustomerDto> getById(@PathVariable("turkishIdNo") String turkishIdNo){
        CustomerDto customerDto = customerService.getCustomerById(turkishIdNo);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<CustomerDto> update(@Valid @RequestBody UpdateCustomerRequest customerUpdateRequest){
        CustomerDto customerDto = customerService.updateCustomer(customerUpdateRequest);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @DeleteMapping(path="/{turkishIdNo}")
    public ResponseEntity<String> delete(@PathVariable("turkishIdNo") String turkishIdNo){
        String result = customerService.deleteCustomer(turkishIdNo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
