package pl.simcode.ing.transactions.api.dto;

import java.math.BigDecimal;

public record AccountDto(
        String account,
        Integer debitCount,
        Integer creditCount,
        BigDecimal balance
) {
}
