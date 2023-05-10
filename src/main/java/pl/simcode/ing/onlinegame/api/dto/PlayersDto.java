package pl.simcode.ing.onlinegame.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PlayersDto(
        @NotNull
        @Min(1)
        @Max(1000)
        Integer groupCount,

        @NotNull
        @Size(min = 1, max = 20000)
        @Valid
        List<ClanDto> clans
) {
}
