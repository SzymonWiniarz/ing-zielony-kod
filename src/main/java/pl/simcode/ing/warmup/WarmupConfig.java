package pl.simcode.ing.warmup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class WarmupConfig {

    @Bean
    @ConditionalOnProperty(name = "warmup.enabled", havingValue = "true")
    ApplicationWarmupListener applicationWarmupListener(@Value("${server.port}") int serverPort, @Value("${warmup.threshold.millis}") int warmupDoneThreshold) {
        var warmupHttpClient = new WarmupHttpClient(serverPort, warmupDoneThreshold);
        return new ApplicationWarmupListener(warmupHttpClient);
    }

}
