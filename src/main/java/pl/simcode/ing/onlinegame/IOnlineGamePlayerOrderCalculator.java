package pl.simcode.ing.onlinegame;

import pl.simcode.ing.onlinegame.api.dto.ClanDto;
import pl.simcode.ing.onlinegame.api.dto.PlayersDto;

public interface IOnlineGamePlayerOrderCalculator {

    ClanDto[][] calculateOrder(PlayersDto players);

}
