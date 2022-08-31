package com.example.graphql.api.resolver.query;

import com.example.graphql.configuration.TestApplication;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {TestApplication.class})
class BankAccountQueryResolverIntegrationTest {

    private static final String GRAPHQL_QUERY_REQUEST_PATH = "graphql/resolver/query/request/%s.graphql";
    private static final String GRAPHQL_QUERY_RESPONSE_PATH = "graphql/resolver/query/response/%s.json";

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void shouldReturnTheBankAccounts() throws IOException, JSONException {
        String testFileName = "bank_account";
        GraphQLResponse graphQLResponse = graphQLTestTemplate
                .postForResource(String.format(GRAPHQL_QUERY_REQUEST_PATH, testFileName));

        String expectedResponseBody = read(String.format(GRAPHQL_QUERY_RESPONSE_PATH, testFileName));
        assertEquals(expectedResponseBody, graphQLResponse.getRawResponse().getBody(), true);

        assertThat(graphQLResponse.getStatusCode()).isEqualTo(OK);
    }

    private String read(String fileToRead) throws IOException {
        return IOUtils.toString(new ClassPathResource(fileToRead).getInputStream(), StandardCharsets.UTF_8);
    }

}