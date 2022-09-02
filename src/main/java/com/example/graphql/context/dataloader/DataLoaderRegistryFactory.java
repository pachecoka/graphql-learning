package com.example.graphql.context.dataloader;

import com.example.graphql.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.dataloader.BatchLoaderEnvironment;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.lang.Runtime.getRuntime;

@Component
@RequiredArgsConstructor
public class DataLoaderRegistryFactory {

    public static final String BALANCE_DATA_LOADER = "BALANCE_DATA_LOADER";
    private static final Executor balanceThreadPool = Executors.newFixedThreadPool(getRuntime().availableProcessors());

    private final BalanceService balanceService;

    public DataLoaderRegistry create(String userId) {
        DataLoaderRegistry registry = new DataLoaderRegistry();

        registry.register(BALANCE_DATA_LOADER, createBalanceDataLoader(userId));

        return registry;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private DataLoader<UUID, BigDecimal> createBalanceDataLoader(String userId) {
        return DataLoader.newMappedDataLoader((bankAccountIds, environment) ->
                CompletableFuture.supplyAsync(() ->
                                balanceService.getBalanceFor((Map)environment.getKeyContexts(), userId),
                        balanceThreadPool));
    }
}
