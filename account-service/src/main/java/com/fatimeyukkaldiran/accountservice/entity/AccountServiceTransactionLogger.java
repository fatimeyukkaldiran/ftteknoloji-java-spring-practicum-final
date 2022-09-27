package com.fatimeyukkaldiran.accountservice.entity;

import com.fatimeyukkaldiran.accountservice.entity.enums.Currency;
import com.fatimeyukkaldiran.accountservice.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_logger")
public class AccountServiceTransactionLogger {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "transaction_id", nullable = false, updatable = false)
    private String id;

    private String customerId;
    private BigDecimal balance_before;
    private BigDecimal balance_after;

    private BigDecimal transaction_amount = BigDecimal.ONE;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private Currency transaction_currency;

    private LocalDateTime transaction_date;



}
