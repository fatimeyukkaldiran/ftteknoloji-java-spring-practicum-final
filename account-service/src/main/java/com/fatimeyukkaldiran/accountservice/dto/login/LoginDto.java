package com.fatimeyukkaldiran.accountservice.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank
    @Size(max = 20, min = 3)
    private String username;

    @NotBlank
    @Size(max = 10, min = 6)
    private String password;
}
