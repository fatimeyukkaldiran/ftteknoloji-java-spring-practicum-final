package com.fatimeyukkaldiran.accountservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class Login {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "login_id", nullable = false, updatable = false)
    private String id;


    @Column(name = "user_name", nullable = false)
    @Size(max = 20, min = 3)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false)
    @Size(max = 10, min = 6)
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
