package pl.simcode.ing.atmservice;

import pl.simcode.ing.atmservice.api.dto.TaskDto;
import pl.simcode.ing.atmservice.api.dto.AtmDto;

import java.util.List;

public interface IAtmServiceOrderCalculator {

    List<AtmDto> calculateOrder(List<TaskDto> tasks);

}
