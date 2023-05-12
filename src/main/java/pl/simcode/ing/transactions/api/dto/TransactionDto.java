package pl.simcode.ing.transactions.api.dto;

import pl.simcode.ing.exceptions.ValidationException;

import java.math.BigDecimal;

public record TransactionDto(
        String debitAccount,

        String creditAccount,

        BigDecimal amount
) {

    public TransactionDto {
        if (debitAccount == null || debitAccount.length() != 26) {
            throw new ValidationException(TransactionDto.class, "debitAccount", debitAccount);
        }

        if (creditAccount == null || creditAccount.length() != 26) {
            throw new ValidationException(TransactionDto.class, "creditAccount", creditAccount);
        }

        if (amount == null) {
            throw new ValidationException(TransactionDto.class, "amount", null);
        }
    }
}
