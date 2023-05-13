package pl.simcode.ing.onlinegame;

import pl.simcode.ing.onlinegame.api.dto.ClanDto;
import pl.simcode.ing.onlinegame.api.dto.PlayersDto;

import java.util.Arrays;

class OnlineGamePlayerOrderCalculator implements IOnlineGamePlayerOrderCalculator {

    private final ClansComparator clansComparator;

    OnlineGamePlayerOrderCalculator(ClansComparator clansComparator) {
        this.clansComparator = clansComparator;
    }

    @Override
    public ClanDto[][] calculateOrder(PlayersDto players) {
        ClanDto[] clansSortedByPoints = players.clans();
        Arrays.parallelSort(clansSortedByPoints, clansComparator);

        var clanGroups = new ClanGroups(players.groupCount());

        for (ClanDto clan : clansSortedByPoints) {
            clanGroups.addClanToFirstGroupWithEnoughCapacity(clan);
        }

        return clanGroups.toDto();
    }

}
