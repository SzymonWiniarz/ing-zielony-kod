package pl.simcode.ing.onlinegame.api.dto;

import jakarta.validation.constraints.NotNull;

public record ClanDto(
        @NotNull
        Integer numberOfPlayers,

        @NotNull
        Integer points
) {
}
