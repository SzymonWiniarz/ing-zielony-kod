package pl.simcode.ing.onlinegame;

import pl.simcode.ing.onlinegame.api.dto.ClanDto;
import pl.simcode.ing.onlinegame.api.dto.PlayersDto;

import java.util.List;

public interface IOnlineGamePlayerOrderCalculator {

    List<List<ClanDto>> calculateOrder(PlayersDto players);

}
