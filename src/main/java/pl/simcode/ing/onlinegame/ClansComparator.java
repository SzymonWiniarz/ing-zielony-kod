package pl.simcode.ing.onlinegame;

import pl.simcode.ing.onlinegame.api.dto.ClanDto;

import java.util.Comparator;

class ClansComparator implements Comparator<ClanDto> {

    private final Comparator<ClanDto> comparatorByPoints = Comparator.comparingInt(ClanDto::points).reversed();
    private final Comparator<ClanDto> comparatorByPlayersCount = Comparator.comparingInt(ClanDto::numberOfPlayers);

    @Override
    public int compare(ClanDto clan1, ClanDto clan2) {
        int comparedByPoints = comparatorByPoints.compare(clan1, clan2);

        if (comparedByPoints != 0) {
            return comparedByPoints;
        }

        return comparatorByPlayersCount.compare(clan1, clan2);
    }

}
