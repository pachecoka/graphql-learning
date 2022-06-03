package com.example.graphql.api.resolver.mutation;

import com.example.graphql.domain.bank.BankAccount;
import com.example.graphql.domain.bank.Client;
import com.example.graphql.domain.bank.input.CreateBankAccountInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.example.graphql.domain.bank.Currency.USD;

@Component
@Slf4j
public class BankAccountMutation implements GraphQLMutationResolver {

    public BankAccount createBankAccount(CreateBankAccountInput input) {
        log.info("Creating bank account for {}", input);
        return BankAccount.builder()
                .id(UUID.randomUUID())
                .currency(USD)
                .client(Client.builder()
                        .firstName(input.getFirstName())
                        .age(input.getAge())
                        .build())
                .build();
    }
}
