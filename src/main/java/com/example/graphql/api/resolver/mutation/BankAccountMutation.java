package com.example.graphql.api.resolver.mutation;

import com.example.graphql.domain.bank.BankAccount;
import com.example.graphql.domain.bank.input.CreateBankAccountInput;
import com.example.graphql.publisher.BankAccountPublisher;
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
    private final BankAccountPublisher publisher;

    public BankAccount createBankAccount(@Valid CreateBankAccountInput input) {
        log.info("Creating bank account for {}", input);
        return createBankAccountWithId(UUID.randomUUID());
    }

    public BankAccount updateBankAccount(UUID id, String name, int age) {
        log.info("Updating bank account for {}. Name: {}, age: {}", id, name, age);
        return createBankAccountWithId(id);
    }

    private BankAccount createBankAccountWithId(UUID id) {
        BankAccount bankAccount = BankAccount.builder()
                .id(id)
                .currency(USD)
                .createdOn(LocalDate.now(clock))
                .createdAt(ZonedDateTime.now(clock))
                .build();

        publisher.publish(bankAccount);
        return bankAccount;
    }
}
