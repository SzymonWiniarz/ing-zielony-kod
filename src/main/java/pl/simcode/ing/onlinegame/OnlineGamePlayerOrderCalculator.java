package pl.simcode.ing.onlinegame;

import pl.simcode.ing.onlinegame.api.dto.ClanDto;
import pl.simcode.ing.onlinegame.api.dto.PlayersDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

class OnlineGamePlayerOrderCalculator implements IOnlineGamePlayerOrderCalculator {

    private final ClansComparator clansComparator;

    OnlineGamePlayerOrderCalculator(ClansComparator clansComparator) {
        this.clansComparator = clansComparator;
    }

    @Override
    public List<List<ClanDto>> calculateOrder(PlayersDto players) {
        List<ClanDto> clansSortedByPoints = new ArrayList<>(players.clans());
        clansSortedByPoints.sort(clansComparator);

        List<List<ClanDto>> playerGroups = new ArrayList<>();

        int currentGroupVacancies = players.groupCount();
        List<ClanDto> currentGroup = new ArrayList<>();

        while (!clansSortedByPoints.isEmpty()) {
            var nextClanOptional = fetchNextClanWithAtMostPlayers(currentGroupVacancies, clansSortedByPoints);

            if (nextClanOptional.isEmpty()) {
                if (!currentGroup.isEmpty()) {
                    playerGroups.add(currentGroup);
                }

                currentGroup = new ArrayList<>();
                currentGroupVacancies = players.groupCount();
            } else {
                var nextClan = nextClanOptional.get();
                currentGroup.add(nextClan);
                currentGroupVacancies -= nextClan.numberOfPlayers();
            }
        }

        if (!currentGroup.isEmpty()) {
            playerGroups.add(currentGroup);
        }

        return playerGroups;
    }

    private Optional<ClanDto> fetchNextClanWithAtMostPlayers(int maxPlayerCount, List<ClanDto> clansSortedByPoints) {
        Iterator<ClanDto> clansIterator = clansSortedByPoints.iterator();

        while (clansIterator.hasNext()) {
            ClanDto clan = clansIterator.next();
            if (clan.numberOfPlayers() <= maxPlayerCount) {
                clansIterator.remove();
                return Optional.of(clan);
            }
        }

        return Optional.empty();
    }

}
