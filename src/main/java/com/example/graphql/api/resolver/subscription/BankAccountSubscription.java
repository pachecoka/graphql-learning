package com.example.graphql.api.resolver.subscription;

import com.example.graphql.domain.bank.BankAccount;
import com.example.graphql.publisher.BankAccountPublisher;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class BankAccountSubscription implements GraphQLSubscriptionResolver {

    private final BankAccountPublisher publisher;

    public Publisher<BankAccount> bankAccounts() {
        return publisher.getBankAccountPublisher();
    }

    public Publisher<BankAccount> bankAccount(UUID id) {
        return publisher.getBankAccountPublisherFor(id);
    }
}
