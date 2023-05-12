package pl.simcode.ing.transactions.api.rest;

import com.fasterxml.jackson.core.JsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TransactionsRestConfig {

    @Bean
    TransactionsJsonParser transactionsJsonParser(@Value("${transactions.batch.size}") int transactionsBatchSize) {
        var jsonFactory = new JsonFactory();
        return new TransactionsJsonParser(jsonFactory, transactionsBatchSize);
    }

}
