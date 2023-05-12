package pl.simcode.ing.transactions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TransactionsConfig {

    @Bean
    ITransactionsReportGeneratorFactory transactionsReportGeneratorFactory() {
        var accountsComparator = new AccountsComparator();
        return new TransactionsReportGeneratorFactory(accountsComparator);
    }

}
