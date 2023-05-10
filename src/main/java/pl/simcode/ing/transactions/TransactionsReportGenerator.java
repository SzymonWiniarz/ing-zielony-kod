package pl.simcode.ing.transactions;

import pl.simcode.ing.transactions.api.dto.AccountDto;
import pl.simcode.ing.transactions.api.dto.TransactionDto;

import java.util.*;

class TransactionsReportGenerator implements ITransactionsReportGenerator {

    @Override
    public List<AccountDto> generateReport(List<TransactionDto> transactions) {
        var accounts = processTransactions(transactions);
        accounts.sort(Comparator.comparing(Account::getAccountNumber));

        return accounts
                .stream()
                .map(Account::toDto)
                .toList();
    }

    private List<Account> processTransactions(List<TransactionDto> transactions) {
        Map<String, Account> accountsByNumber = new HashMap<>();

        for (var transaction : transactions) {
            var debitAccount = accountsByNumber.computeIfAbsent(transaction.debitAccount(), Account::new);
            debitAccount.debit(transaction.amount());

            var creditAccount = accountsByNumber.computeIfAbsent(transaction.creditAccount(), Account::new);
            creditAccount.credit(transaction.amount());
        }

        return new ArrayList<>(accountsByNumber.values());
    }


}
