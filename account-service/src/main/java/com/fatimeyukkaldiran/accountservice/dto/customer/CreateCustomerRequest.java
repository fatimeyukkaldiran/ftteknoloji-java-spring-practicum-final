package com.fatimeyukkaldiran.accountservice.dto.customer;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import java.sql.Date;

@Data
public class CreateCustomerRequest {

    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Email
    private String email;
    private String phoneNumber;
    private String turkishRepublicIdNo;
}
