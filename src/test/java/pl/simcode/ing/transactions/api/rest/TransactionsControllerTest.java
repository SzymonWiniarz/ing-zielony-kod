package pl.simcode.ing.transactions.api.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import pl.simcode.ing.BaseEndpointTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionsControllerTest extends BaseEndpointTest {

    @Test
    @DisplayName("Should respond with HTTP 400 status and error response body when POST /transactions/report is called with invalid request")
    void testInvalidTransactionsRequest() throws Exception {
        testInvalidRequest("/transactions/report", "transactions");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "example",
            "example_generated_1",
            "example_generated_2"
    })
    @DisplayName("Should respond with HTTP 200 status and expected response body for each provided example request when POST /transactions/report is called")
    void testOnProvidedValidTransactionsExamples(String exampleName) throws Exception {
        testProvidedValidExamples("/transactions/report", "transactions", exampleName);
    }
}