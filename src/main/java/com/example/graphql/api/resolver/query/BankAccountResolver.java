package com.example.graphql.api.resolver.query;

import com.example.graphql.connection.CursorUtil;
import com.example.graphql.context.CustomGraphQLContext;
import com.example.graphql.domain.bank.BankAccount;
import com.example.graphql.repository.BankAccountRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.*;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static com.example.graphql.domain.bank.Currency.USD;
import static java.util.stream.Collectors.toUnmodifiableList;

@Slf4j
@Component
@RequiredArgsConstructor
public class BankAccountResolver implements GraphQLQueryResolver {

    private final Clock clock;
    private final BankAccountRepository bankAccountRepository;
    private final CursorUtil cursorUtil;

    public BankAccount getBankAccount(UUID id, DataFetchingEnvironment environment) {
        CustomGraphQLContext context = environment.getContext();
        log.info("User id from context: {}", context.getUserId());

        log.info("Retrieving bank account with id: {}", id);
        return BankAccount.builder()
                .id(id)
                .currency(USD)
                .createdAt(ZonedDateTime.now(clock))
                .createdOn(LocalDate.now(clock))
                .build();
    }

    public Connection<BankAccount> getBankAccounts(int first, @Nullable String cursor) {
        List<BankAccount> bankAccounts = getBankAccounts(cursor);

        List<Edge<BankAccount>> edges = bankAccounts
                .stream()
                .limit(first)
                .map(bankAccount -> new DefaultEdge<>(bankAccount, cursorUtil.createCursorWith(bankAccount.getId())))
                .collect(toUnmodifiableList());

        ConnectionCursor startCursor = cursorUtil.getStartCursorFrom(edges);
        ConnectionCursor endCursor = cursorUtil.getEndCursorFrom(edges);

        DefaultPageInfo pageInfo = new DefaultPageInfo(startCursor, endCursor, cursor != null, edges.size() >= first);

        return new DefaultConnection<>(edges, pageInfo);
    }

    private List<BankAccount> getBankAccounts(String cursorId) {
        if(StringUtils.isEmpty(cursorId)) {
            return bankAccountRepository.getBankAccounts();
        }

        return bankAccountRepository.getBankAccountsAfter(cursorUtil.decode(cursorId));
    }
}
