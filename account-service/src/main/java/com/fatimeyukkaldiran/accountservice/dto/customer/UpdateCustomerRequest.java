package com.fatimeyukkaldiran.accountservice.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequest {
    private String turkishRepublicIdNo;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;

}
