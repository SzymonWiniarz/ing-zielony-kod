package pl.simcode.ing.onlinegame.api.dto;

import pl.simcode.ing.exceptions.ValidationException;

public record ClanDto(
        Integer numberOfPlayers,
        Integer points
) {

    public ClanDto {
        if (numberOfPlayers == null || numberOfPlayers < 1 || numberOfPlayers > 1000) {
            throw new ValidationException(ClanDto.class, "numberOfPlayers", numberOfPlayers);
        }

        if (points == null || points < 0 || points > 1_000_000) {
            throw new ValidationException(ClanDto.class, "points", points);
        }
    }
}
