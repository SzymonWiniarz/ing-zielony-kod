package pl.simcode.ing.transactions;

import pl.simcode.ing.transactions.api.dto.AccountDto;

import java.util.Comparator;

class AccountsComparator implements Comparator<AccountDto> {

    private static final Comparator<AccountDto> COMPARATOR_BY_ACCOUNT_NUMBER = Comparator.comparing(AccountDto::account);

    @Override
    public int compare(AccountDto account1, AccountDto account2) {
        return COMPARATOR_BY_ACCOUNT_NUMBER.compare(account1, account2);
    }

}
