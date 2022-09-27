package com.fatimeyukkaldiran.accountservice.controller;

import com.fatimeyukkaldiran.accountservice.dto.login.LoginDto;
import com.fatimeyukkaldiran.accountservice.service.LoginService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final LoginService loginService;

    public LoginController(BCryptPasswordEncoder bCryptPasswordEncoder, LoginService loginService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.loginService = loginService;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody LoginDto loginDto) {
        loginDto.setPassword(bCryptPasswordEncoder.encode(loginDto.getPassword()));
        loginService.save(loginDto);
    }

}