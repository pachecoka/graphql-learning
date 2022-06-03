package com.example.graphql.api.resolver.query;

import com.example.graphql.domain.bank.BankAccount;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.example.graphql.domain.bank.Currency.USD;

@Component
@Slf4j
public class BankAccountResolver implements GraphQLQueryResolver {

    public BankAccount getBankAccount(UUID id) {
        log.info("Retrieving bank account with id: {}", id);
        return BankAccount.builder()
                .id(id)
                .currency(USD)
                .build();
    }
}
