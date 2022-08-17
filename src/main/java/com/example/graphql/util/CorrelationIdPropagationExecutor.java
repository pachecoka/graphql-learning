package com.example.graphql.util;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;

import java.util.concurrent.Executor;

@RequiredArgsConstructor
public class CorrelationIdPropagationExecutor implements Executor {

    public static final String CORRELATION_ID = "correlation_id";
    private final Executor delegate;

    // this is for when new thread are created and executed on the code, because the MDC context will be lost
    public static Executor wrap(Executor executor) {
        return new CorrelationIdPropagationExecutor(executor);
    }

    @Override
    public void execute(@NotNull Runnable command) {
        String correlationId = MDC.get(CORRELATION_ID);
        delegate.execute(() -> {
            try {
                MDC.put(CORRELATION_ID, correlationId);
                command.run();
            } finally {
                MDC.remove(CORRELATION_ID);
            }
        });
    }
}
