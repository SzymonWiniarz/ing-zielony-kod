package pl.simcode.ing.warmup;

import org.slf4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import static org.slf4j.LoggerFactory.getLogger;

class WarmupHttpClient {

    private static final Logger LOGGER = getLogger(WarmupHttpClient.class);
    private final HttpClient httpClient;
    private final int serverPort;
    private final int warmupDoneThreshold;

    WarmupHttpClient(int serverPort, int warmupDoneThresholdInMillis) {
        this.serverPort = serverPort;
        this.warmupDoneThreshold = warmupDoneThresholdInMillis;
        this.httpClient = HttpClient.newHttpClient();
    }

    void warmupEndpoint(String endpointPath, String requestBodyFilePath) {
        long previousCallDurationInMillis;
        long currentCallDurationInMillis = 0;

        do {
            previousCallDurationInMillis = currentCallDurationInMillis;
            long startTime = System.currentTimeMillis();
            callEndpoint(endpointPath, requestBodyFilePath);
            long endTime = System.currentTimeMillis();

            currentCallDurationInMillis = endTime - startTime;
        } while (Math.abs(currentCallDurationInMillis - previousCallDurationInMillis) > warmupDoneThreshold);

        LOGGER.info("Warmup for {} endpoint finished", endpointPath);
    }

    private void callEndpoint(String endpointPath, String requestBodyFilePath) {
        try {
            var uri = new URI("http://localhost:%d/%s".formatted(serverPort, endpointPath));
            var postBody = new ClassPathResource(requestBodyFilePath).getContentAsString(StandardCharsets.UTF_8);
            var postHttpRequest = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(postBody))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            httpClient.send(postHttpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException e) {
            throw new WarmupFailedException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WarmupFailedException(e);
        }
    }

}
