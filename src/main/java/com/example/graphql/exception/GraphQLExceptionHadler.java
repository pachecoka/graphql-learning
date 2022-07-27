package com.example.graphql.exception;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Component
public class GraphQLExceptionHadler {

    @ExceptionHandler({GraphQLException.class, ConstraintViolationException.class})
    public ThrowableGraphQLError handle(Exception exception) {
        return new ThrowableGraphQLError(exception);
    }

    @ExceptionHandler(RuntimeException.class)
    public ThrowableGraphQLError handle(RuntimeException exception) {
        return new ThrowableGraphQLError(exception, "Internal server error");
    }
}
