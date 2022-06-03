package com.example.graphql.domain.bank;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class Client {
    UUID id;
    String firstName;
    String lastName;
    int age;
}
