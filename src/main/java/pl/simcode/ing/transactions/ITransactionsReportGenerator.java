package pl.simcode.ing.transactions;

import pl.simcode.ing.transactions.api.dto.AccountDto;
import pl.simcode.ing.transactions.api.dto.TransactionDto;

public interface ITransactionsReportGenerator {

    AccountDto[] generateReport(TransactionDto[] transactions);

}
