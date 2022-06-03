package com.example.graphql.api.resolver;

import com.example.graphql.domain.bank.BankAccount;
import com.example.graphql.domain.bank.Client;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ClientResolver implements GraphQLResolver<BankAccount> {

    public Client getClient(BankAccount bankAccount) {
        log.info("Retrieving client for bank account with id: {}", bankAccount.getId());
//        throw new RuntimeException("Client unavailable");
        return Client.builder()
                .id(UUID.randomUUID())
                .firstName("Karolina")
                .lastName("Pacheco")
                .build();
    }
}
