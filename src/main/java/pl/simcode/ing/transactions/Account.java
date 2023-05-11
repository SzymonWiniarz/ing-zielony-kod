package pl.simcode.ing.transactions;

import pl.simcode.ing.transactions.api.dto.AccountDto;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

class Account {
    private final String accountNumber;
    private final AtomicInteger debitCount = new AtomicInteger(0);
    private final AtomicInteger creditCount = new AtomicInteger(0);
    private final AtomicReference<BigDecimal> balance = new AtomicReference<>(BigDecimal.ZERO);

    Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    void debit(BigDecimal amount) {
        debitCount.incrementAndGet();
        balance.accumulateAndGet(amount, BigDecimal::subtract);
    }

    void credit(BigDecimal amount) {
        creditCount.incrementAndGet();
        balance.accumulateAndGet(amount, BigDecimal::add);
    }

    AccountDto toDto() {
        return new AccountDto(accountNumber, debitCount.get(), creditCount.get(), balance.get());
    }

}
