package pl.simcode.ing.onlinegame.api.dto;

import pl.simcode.ing.exceptions.ValidationException;

public record PlayersDto(
        Integer groupCount,
        ClanDto[] clans
) {

    public PlayersDto {
        if (groupCount == null || groupCount < 1 || groupCount > 1000) {
            throw new ValidationException(PlayersDto.class, "groupCount", groupCount);
        }

        if (clans == null || clans.length == 0 || clans.length > 20000) {
            throw new ValidationException(PlayersDto.class, "clans", clans);
        }
    }
}
