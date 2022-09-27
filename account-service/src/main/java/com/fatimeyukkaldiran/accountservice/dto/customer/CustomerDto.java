package com.fatimeyukkaldiran.accountservice.dto.customer;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fatimeyukkaldiran.accountservice.dto.account.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String id;
    private String firstName;
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Email
    private String email;

    private String phoneNumber;
    private String turkishRepublicIdNo;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AccountResponse> accounts = new ArrayList<>();
}
