package pl.simcode.ing.transactions;

import pl.simcode.ing.transactions.api.dto.AccountDto;
import pl.simcode.ing.transactions.api.dto.TransactionDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class TransactionsReportGenerator implements ITransactionsReportGenerator {

    private final AccountsComparator accountsComparator;
    private final Map<String, Account> accountsByNumber;

    TransactionsReportGenerator(AccountsComparator accountsComparator) {
        this.accountsComparator = accountsComparator;
        this.accountsByNumber = new ConcurrentHashMap<>();
    }

    @Override
    public void processTransactionsBatch(List<TransactionDto> transactionsBatch) {
        transactionsBatch.stream()
                .parallel()
                .forEach(transaction -> {
                    var debitAccount = accountsByNumber.computeIfAbsent(transaction.debitAccount(), Account::new);
                    debitAccount.debit(transaction.amount());

                    var creditAccount = accountsByNumber.computeIfAbsent(transaction.creditAccount(), Account::new);
                    creditAccount.credit(transaction.amount());
                });
    }

    @Override
    public AccountDto[] generateReport() {
        var accounts = accountsByNumber.values().toArray(Account[]::new);

        return Arrays.stream(accounts)
                .parallel()
                .map(Account::toDto)
                .sorted(accountsComparator)
                .toArray(AccountDto[]::new);
    }


}
