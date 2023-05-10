package pl.simcode.ing.transactions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TransactionsConfig {

    @Bean
    ITransactionsReportGenerator transactionsReportGenerator() {
        return new TransactionsReportGenerator();
    }

}
