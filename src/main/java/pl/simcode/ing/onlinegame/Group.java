package pl.simcode.ing.onlinegame;

import pl.simcode.ing.onlinegame.api.dto.ClanDto;

import java.util.ArrayList;
import java.util.List;

class Group {

    private final List<ClanDto> clans;
    private int remainingCapacity;

    Group(int capacity) {
        this.clans =  new ArrayList<>();
        this.remainingCapacity = capacity;
    }

    boolean addClan(ClanDto clan) {
        if (clan.numberOfPlayers() > remainingCapacity) {
            return false;
        }

        remainingCapacity -= clan.numberOfPlayers();
        return clans.add(clan);
    }

    List<ClanDto> getClans() {
        return clans;
    }

}
