package pl.simcode.ing.atmservice.api.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import pl.simcode.ing.BaseEndpointTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AtmServiceControllerTest extends BaseEndpointTest {

    @Test
    @DisplayName("Should respond with HTTP 400 status and error response body when POST /atms/calculateOrder is called with invalid request")
    void testInvalidAtmsRequest() throws Exception {
        testInvalidRequest("/atms/calculateOrder", "atms");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "example_1",
            "example_2",
            "one_atm_per_region",
            "all_atm_types"
    })
    @DisplayName("Should respond with HTTP 200 status and expected response body for each provided example request when POST /atms/calculateOrder is called")
    void testProvidedValidAtmsExamples(String exampleName) throws Exception {
        testProvidedValidExamples("/atms/calculateOrder", "atms", exampleName);
    }

}