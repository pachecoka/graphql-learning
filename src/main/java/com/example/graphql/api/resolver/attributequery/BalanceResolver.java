package com.example.graphql.api.resolver.attributequery;

import com.example.graphql.domain.bank.BankAccount;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.example.graphql.context.dataloader.DataLoaderRegistryFactory.BALANCE_DATA_LOADER;

@Slf4j
@Component
public class BalanceResolver implements GraphQLResolver<BankAccount> {

    public CompletableFuture<BigDecimal> balance(BankAccount bankAccount, DataFetchingEnvironment environment) {
        log.info("Getting balance for {}", bankAccount.getId());
        DataLoader<UUID, BigDecimal> dataLoader = environment.getDataLoader(BALANCE_DATA_LOADER);
        return dataLoader.load(bankAccount.getId(), bankAccount);
    }
}
