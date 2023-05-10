package pl.simcode.ing.atmservice;

import pl.simcode.ing.atmservice.api.dto.TaskDto;

import java.util.Comparator;

class TasksComparator implements Comparator<TaskDto> {

    private static final Comparator<TaskDto> COMPARATOR_BY_REGION = Comparator.comparingInt(TaskDto::region);
    private static final Comparator<TaskDto> COMPARATOR_BY_PRIORITY = Comparator.comparingInt(task -> task.requestType().priority());
    private static final Comparator<TaskDto> COMBINED_COMPARATOR = COMPARATOR_BY_REGION.thenComparing(COMPARATOR_BY_PRIORITY);

    @Override
    public int compare(TaskDto task1, TaskDto task2) {
        return COMBINED_COMPARATOR.compare(task1, task2);
    }

}
