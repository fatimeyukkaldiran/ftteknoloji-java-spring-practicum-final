package com.fatimeyukkaldiran.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "customer_id", nullable = false, updatable = false)
    private String id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Email
    @Column(name = "email", unique = true, length = 50, nullable = false)
    private String email;


    @Column(name = "TELEPHONE", length = 15, nullable = false)
    private String phoneNumber;

    @Column(name = "turkish_republic_id_no", length = 11, unique = true, nullable = false, updatable = false)
    private String turkishRepublicIdNo;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "customer")
    private Set<Account> accounts = new HashSet<>();
}
