package pl.simcode.ing.transactions;

import pl.simcode.ing.transactions.api.dto.AccountDto;
import pl.simcode.ing.transactions.api.dto.TransactionDto;

import java.util.List;

public interface ITransactionsReportGenerator {

    void processTransactionsBatch(List<TransactionDto> transactionsBatch);

    AccountDto[] generateReport();

}
