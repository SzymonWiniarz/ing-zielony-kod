package pl.simcode.ing.transactions;

import pl.simcode.ing.transactions.api.dto.AccountDto;

import java.math.BigDecimal;

class Account {
    private final String accountNumber;
    private int debitCount = 0;
    private int creditCount = 0;
    private BigDecimal balance = BigDecimal.ZERO;

    Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    void debit(BigDecimal amount) {
        debitCount++;
        balance = balance.subtract(amount);
    }

    void credit(BigDecimal amount) {
        creditCount++;
        balance = balance.add(amount);
    }

    AccountDto toDto() {
        return new AccountDto(accountNumber, debitCount, creditCount, balance);
    }

}
