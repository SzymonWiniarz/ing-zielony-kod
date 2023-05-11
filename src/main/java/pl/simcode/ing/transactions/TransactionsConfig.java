package pl.simcode.ing.transactions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TransactionsConfig {

    @Bean
    ITransactionsReportGenerator transactionsReportGenerator() {
        var accountsComparator = new AccountsComparator();
        return new TransactionsReportGenerator(accountsComparator);
    }

}
