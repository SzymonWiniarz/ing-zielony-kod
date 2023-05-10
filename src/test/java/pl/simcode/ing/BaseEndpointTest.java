package pl.simcode.ing;

import org.hamcrest.text.IsEqualCompressingWhiteSpace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public abstract class BaseEndpointTest {

    @Value(value = "${local.server.port}")
    private int port;

    protected void testInvalidRequest(String endpointPath, String examplesDirectory) throws Exception {
        var requestBody = new ClassPathResource(String.format("/%s/invalid_request.json", examplesDirectory)).getContentAsString(StandardCharsets.UTF_8);

        given()
                .port(port)
                .body(requestBody)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).
        when()
                .post(endpointPath).
        then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(MediaType.APPLICATION_JSON.getType());
    }

    protected void testProvidedValidExamples(String endpointPath, String examplesDirectory, String exampleName) throws Exception {
        var requestBody = new ClassPathResource(String.format("/%s/%s_request.json", examplesDirectory, exampleName)).getContentAsString(StandardCharsets.UTF_8);
        var expectedResponseBody = new ClassPathResource(String.format("/%s/%s_response.json", examplesDirectory, exampleName)).getContentAsString(StandardCharsets.UTF_8);

        given()
                .port(port)
                .body(requestBody)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).
        when()
                .post(endpointPath).
        then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON.getType())
                .body(new IgnoreAllWhitespacesMatcher(expectedResponseBody));
    }

    private static class IgnoreAllWhitespacesMatcher extends IsEqualCompressingWhiteSpace {

        public IgnoreAllWhitespacesMatcher(String string) {
            super(string);
        }

        @Override
        public String stripSpaces(String toBeStripped) {
            return toBeStripped
                    .replaceAll("[\r\n]+", "")
                    .replaceAll("\\s+", "").trim();
        }
    }

}
