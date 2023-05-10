package pl.simcode.ing.atmservice.api.rest;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.simcode.ing.atmservice.IAtmServiceOrderCalculator;
import pl.simcode.ing.atmservice.api.dto.AtmDto;
import pl.simcode.ing.atmservice.api.dto.TaskDto;

import java.util.List;

@RestController
@RequestMapping("atms")
@Validated
public class AtmServiceController {

    private final IAtmServiceOrderCalculator atmServiceOrderCalculator;

    public AtmServiceController(IAtmServiceOrderCalculator atmServiceOrderCalculator) {
        this.atmServiceOrderCalculator = atmServiceOrderCalculator;
    }

    @PostMapping("/calculateOrder")
    public List<AtmDto> calculateOrder(@Valid @RequestBody List<TaskDto> tasks) {
        return atmServiceOrderCalculator.calculateOrder(tasks);
    }

}
