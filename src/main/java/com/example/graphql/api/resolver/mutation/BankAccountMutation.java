package com.example.graphql.api.resolver.mutation;

import com.example.graphql.domain.bank.BankAccount;
import com.example.graphql.domain.bank.Client;
import com.example.graphql.domain.bank.input.CreateBankAccountInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.example.graphql.domain.bank.Currency.USD;

@Component
@Slf4j
@RequiredArgsConstructor
@Validated
public class BankAccountMutation implements GraphQLMutationResolver {

    private final Clock clock;

    public BankAccount createBankAccount(@Valid CreateBankAccountInput input) {
        log.info("Creating bank account for {}", input);
        return BankAccount.builder()
                .id(UUID.randomUUID())
                .currency(USD)
                .client(Client.builder()
                        .firstName(input.getFirstName())
                        .age(input.getAge())
                        .build())
                .createdOn(LocalDate.now(clock))
                .createdAt(ZonedDateTime.now(clock))
                .build();
    }
}
