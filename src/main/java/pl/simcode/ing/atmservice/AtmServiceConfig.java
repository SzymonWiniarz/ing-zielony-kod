package pl.simcode.ing.atmservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AtmServiceConfig {

    @Bean
    IAtmServiceOrderCalculator atmServiceOrderCalculator() {
        var tasksComparator = new TasksComparator();
        return new AtmServiceOrderCalculator(tasksComparator);
    }

}
