package pl.simcode.ing.transactions.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.simcode.ing.transactions.BalanceSerializer;

import java.math.BigDecimal;

public record AccountDto(
        String account,
        Integer debitCount,
        Integer creditCount,

        @JsonSerialize(using = BalanceSerializer.class)
        BigDecimal balance
) {
}
