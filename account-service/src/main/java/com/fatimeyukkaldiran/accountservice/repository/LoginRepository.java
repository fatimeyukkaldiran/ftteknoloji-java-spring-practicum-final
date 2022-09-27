package com.fatimeyukkaldiran.accountservice.repository;

import com.fatimeyukkaldiran.accountservice.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, String> {
    Login findByUsername(String username);
}
