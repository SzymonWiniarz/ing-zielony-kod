package pl.simcode.ing.atmservice;

import pl.simcode.ing.atmservice.api.dto.AtmDto;
import pl.simcode.ing.atmservice.api.dto.TaskDto;

import java.util.Arrays;

class AtmServiceOrderCalculator implements IAtmServiceOrderCalculator {

    private final TasksComparator tasksComparator;

    AtmServiceOrderCalculator(TasksComparator tasksComparator) {
        this.tasksComparator = tasksComparator;
    }

    @Override
    public AtmDto[] calculateOrder(TaskDto[] tasks) {
        Arrays.parallelSort(tasks, tasksComparator);
        return Arrays.stream(tasks)
                .map(this::getAtmFromTask)
                .distinct()
                .toArray(AtmDto[]::new);
    }

    private AtmDto getAtmFromTask(TaskDto task) {
        return new AtmDto(task.region(), task.atmId());
    }

}
