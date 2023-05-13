package pl.simcode.ing.onlinegame.api.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.simcode.ing.onlinegame.IOnlineGamePlayerOrderCalculator;
import pl.simcode.ing.onlinegame.api.dto.ClanDto;
import pl.simcode.ing.onlinegame.api.dto.PlayersDto;

@RestController
@RequestMapping("onlinegame")
public class OnlineGameController {

    private final IOnlineGamePlayerOrderCalculator playerOrderCalculator;

    public OnlineGameController(IOnlineGamePlayerOrderCalculator playerOrderCalculator) {
        this.playerOrderCalculator = playerOrderCalculator;
    }

    @PostMapping("/calculate")
    public ClanDto[][] calculateOrder(@RequestBody PlayersDto players) {
        return playerOrderCalculator.calculateOrder(players);
    }

}
