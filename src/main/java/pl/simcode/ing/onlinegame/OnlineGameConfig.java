package pl.simcode.ing.onlinegame;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OnlineGameConfig {

    @Bean
    IOnlineGamePlayerOrderCalculator onlineGamePlayerOrderCalculator() {
        var clansComparator = new ClansComparator();
        return new OnlineGamePlayerOrderCalculator(clansComparator);
    }

}
