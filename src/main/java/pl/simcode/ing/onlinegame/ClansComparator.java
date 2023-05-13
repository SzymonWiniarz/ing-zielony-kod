package pl.simcode.ing.onlinegame;

import pl.simcode.ing.onlinegame.api.dto.ClanDto;

import java.util.Comparator;

class ClansComparator implements Comparator<ClanDto> {

    private static final Comparator<ClanDto> COMPARATOR_BY_POINTS = Comparator.comparingInt(ClanDto::points).reversed();
    private static final Comparator<ClanDto> COMPARATOR_BY_PLAYERS_COUNT = Comparator.comparingInt(ClanDto::numberOfPlayers);
    private static final Comparator<ClanDto> COMBINED_COMPARATOR = COMPARATOR_BY_POINTS.thenComparing(COMPARATOR_BY_PLAYERS_COUNT);

    @Override
    public int compare(ClanDto clan1, ClanDto clan2) {
        return COMBINED_COMPARATOR.compare(clan1, clan2);
    }

}
