package pl.simcode.ing.atmservice;

import pl.simcode.ing.atmservice.api.dto.AtmDto;
import pl.simcode.ing.atmservice.api.dto.TaskDto;

public interface IAtmServiceOrderCalculator {

    AtmDto[] calculateOrder(TaskDto[] tasks);

}
