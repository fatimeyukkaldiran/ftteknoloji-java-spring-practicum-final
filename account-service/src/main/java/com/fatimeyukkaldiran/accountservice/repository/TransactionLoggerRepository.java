package com.fatimeyukkaldiran.accountservice.repository;

import com.fatimeyukkaldiran.accountservice.entity.AccountServiceTransactionLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionLoggerRepository extends JpaRepository<AccountServiceTransactionLogger, String> {

    Page<AccountServiceTransactionLogger> findAllByTransaction_date(Pageable pageable);
}
