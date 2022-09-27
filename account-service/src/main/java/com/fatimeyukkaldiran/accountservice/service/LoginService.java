package com.fatimeyukkaldiran.accountservice.service;

import com.fatimeyukkaldiran.accountservice.dto.login.LoginDto;
import com.fatimeyukkaldiran.accountservice.entity.Login;
import com.fatimeyukkaldiran.accountservice.repository.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void save(LoginDto loginDto) {
        mapToDTO(this.loginRepository.save(
                new Login(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        ));
    }

    public static LoginDto mapToDTO(Login login) {
        if (login != null) {
            return new LoginDto(
                    login.getUsername(),
                    login.getPassword()
            );
        }
        return null;
    }
}
