package com.fatimeyukkaldiran.accountservice.repository;

import com.fatimeyukkaldiran.accountservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("SELECT CASE WHEN COUNT(C)>0 THEN TRUE ELSE FALSE END FROM Customer c WHERE C.turkishRepublicIdNo =?1")
    boolean selectExistsTurkishRepublicIdNo(String id);

    Optional<Customer> findCustomerByTurkishRepublicIdNo(String id);


}
