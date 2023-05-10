package pl.simcode.ing.atmservice;

import pl.simcode.ing.atmservice.api.dto.TaskDto;
import pl.simcode.ing.atmservice.api.dto.AtmDto;

import java.util.List;

class AtmServiceOrderCalculator implements IAtmServiceOrderCalculator {

    @Override
    public List<AtmDto> calculateOrder(List<TaskDto> tasks) {
        var tasksBundle = TasksBundle.of(tasks);
        return tasksBundle.createServicePlan();
    }

}
