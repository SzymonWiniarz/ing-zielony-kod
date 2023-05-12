package pl.simcode.ing.onlinegame.api.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.simcode.ing.BaseEndpointTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class OnlineGameControllerTest extends BaseEndpointTest {

    @Test
    @DisplayName("Should respond with HTTP 400 status and error response body when POST /onlinegame/calculate is called with invalid request")
    void testInvalidOnlineGameRequest() throws Exception {
        testInvalidRequest("/onlinegame/calculate", "onlinegame");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "example",
            "example_generated_1",
            "example_generated_2",
            "same_points"
    })
    @DisplayName("Should respond with HTTP 200 status and expected response body for each provided example request when POST /onlinegame/calculate is called")
    void testOnProvidedValidOnlineGameExamples(String exampleName) throws Exception {
        testProvidedValidExamples("/onlinegame/calculate", "onlinegame", exampleName);
    }

}