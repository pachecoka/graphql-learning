package com.example.graphql.domain.bank;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Value
public class BankAccount {

    UUID id;
    Client client;
    Currency currency;
    BigDecimal balance;
    LocalDate createdOn;
    ZonedDateTime createdAt;

}
