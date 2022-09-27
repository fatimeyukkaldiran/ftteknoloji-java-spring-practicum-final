package com.fatimeyukkaldiran.accountservice.service;

import com.fatimeyukkaldiran.accountservice.entity.Login;
import com.fatimeyukkaldiran.accountservice.repository.LoginRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static java.util.Collections.emptyList;
@Service
public class AccountServiceImpl implements UserDetailsService {
     private final LoginRepository loginRepository;

    public AccountServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepository.findByUsername(username);
        if (login == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(login.getUsername(), login.getPassword(), emptyList());
    }
}
