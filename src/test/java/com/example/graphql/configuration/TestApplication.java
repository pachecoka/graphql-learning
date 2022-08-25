package com.example.graphql.configuration;

import com.example.graphql.GraphqlApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

@Configuration
@Import(GraphqlApplication.class)
public class TestApplication {

    @Bean
    @Primary
    public Clock testClock() {
        return Clock.fixed(LocalDate.of(2020, 10, 17).atStartOfDay(UTC).toInstant(), UTC);
    }
}
