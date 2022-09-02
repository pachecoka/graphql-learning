package com.example.graphql.service;

import com.example.graphql.domain.bank.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static java.util.UUID.fromString;

@Slf4j
@Service
public class BalanceService {

    public Map<UUID, BigDecimal> getBalanceFor(Map<UUID, BankAccount> bankAccounts, String userId) {
        Set<UUID> bankAccountIds = bankAccounts.keySet();

        log.info("Requesting balance for bankAccountIds: {} for userId: {}", bankAccountIds, userId);
        return Map.of(fromString("c6aa269a-812b-49d5-b178-a739a1ed74cc"), BigDecimal.TEN,
                fromString("024bb503-5c0f-4d60-aa44-db19d87042f4"), BigDecimal.ONE);
    }
}
