package pl.simcode.ing.transactions;

import pl.simcode.ing.transactions.api.dto.AccountDto;
import pl.simcode.ing.transactions.api.dto.TransactionDto;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class TransactionsReportGenerator implements ITransactionsReportGenerator {

    private final AccountsComparator accountsComparator;

    TransactionsReportGenerator(AccountsComparator accountsComparator) {
        this.accountsComparator = accountsComparator;
    }

    @Override
    public AccountDto[] generateReport(TransactionDto[] transactions) {
        var accounts = processTransactions(transactions);

        return Arrays.stream(accounts)
                .parallel()
                .map(Account::toDto)
                .sorted(accountsComparator)
                .toArray(AccountDto[]::new);
    }

    private Account[] processTransactions(TransactionDto[] transactions) {
        Map<String, Account> accountsByNumber = new ConcurrentHashMap<>();

        Arrays.stream(transactions)
                .parallel()
                .forEach(transaction -> {
                    var debitAccount = accountsByNumber.computeIfAbsent(transaction.debitAccount(), Account::new);
                    debitAccount.debit(transaction.amount());

                    var creditAccount = accountsByNumber.computeIfAbsent(transaction.creditAccount(), Account::new);
                    creditAccount.credit(transaction.amount());
                });

        return accountsByNumber.values().toArray(Account[]::new);
    }


}
