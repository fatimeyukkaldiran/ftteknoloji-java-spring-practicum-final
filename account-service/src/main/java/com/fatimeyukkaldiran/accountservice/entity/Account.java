package com.fatimeyukkaldiran.accountservice.entity;

import com.fatimeyukkaldiran.accountservice.entity.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "account_id", nullable = false, updatable = false)
    private String id;

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 11, fraction = 2)
    private BigDecimal balance;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @JoinColumn(
    name="customer_id",
    foreignKey = @ForeignKey(name = "FK_CUSTOMER_ACCOUNT"),
    nullable = false)
    private Customer customer;
}
