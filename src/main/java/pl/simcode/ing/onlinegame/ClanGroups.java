package pl.simcode.ing.onlinegame;

import pl.simcode.ing.exceptions.ValidationException;
import pl.simcode.ing.onlinegame.api.dto.ClanDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ClanGroups {

    private final List<Group> groups;
    private final int allowedGroupCapacity;

    ClanGroups(int allowedGroupCapacity) {
        this.allowedGroupCapacity = allowedGroupCapacity;
        this.groups = new ArrayList<>();
    }

    void addClanToFirstGroupWithEnoughCapacity(ClanDto clan) {
        validateClan(clan);

        var clanAdded = false;
        Iterator<Group> groupsIterator = groups.iterator();

        while (groupsIterator.hasNext() && !clanAdded) {
            var group = groupsIterator.next();
            clanAdded = group.addClan(clan);
        }

        if (!clanAdded) {
            var newGroup = new Group(allowedGroupCapacity);
            newGroup.addClan(clan);
            groups.add(newGroup);
        }
    }

    private void validateClan(ClanDto clan) {
        if (clan.numberOfPlayers() > allowedGroupCapacity) {
            throw new ValidationException(ClanDto.class, "numberOfPlayers", clan.numberOfPlayers());
        }
    }

    ClanDto[][] toDto() {
        return groups.stream()
                .map(group -> group.getClans().toArray(ClanDto[]::new))
                .toArray(ClanDto[][]::new);
    }

}
