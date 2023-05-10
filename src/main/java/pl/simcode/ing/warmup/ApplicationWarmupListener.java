package pl.simcode.ing.warmup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

class ApplicationWarmupListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationWarmupListener.class);

    private final WarmupHttpClient warmupHttpClient;

    ApplicationWarmupListener(WarmupHttpClient warmupHttpClient) {
        this.warmupHttpClient = warmupHttpClient;
    }

    @EventListener
    public void handleApplicationStarted(ApplicationStartedEvent event) {
        warmUp();
    }

    private void warmUp() {
        LOGGER.info("Starting warm up...");
        warmupHttpClient.warmupEndpoint("atms/calculateOrder", "/atms/warmup_request.json");
        warmupHttpClient.warmupEndpoint("onlinegame/calculate", "/onlinegame/warmup_request.json");
        warmupHttpClient.warmupEndpoint("transactions/report", "/transactions/warmup_request.json");
        LOGGER.info("Warm up finished");
    }

}
