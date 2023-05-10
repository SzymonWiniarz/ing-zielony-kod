package pl.simcode.ing.transactions.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TransactionDto(
        @NotNull
        @Size(min = 26, max = 26)
        String debitAccount,

        @NotNull
        @Size(min = 26, max = 26)
        String creditAccount,

        @NotNull
        BigDecimal amount
) {
}
