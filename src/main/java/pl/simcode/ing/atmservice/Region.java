package pl.simcode.ing.atmservice;

import pl.simcode.ing.atmservice.api.dto.AtmDto;
import pl.simcode.ing.atmservice.api.dto.TaskDto;

import java.util.Comparator;
import java.util.List;

class Region {

    private final int id;
    private final List<TaskDto> tasks;

    static Region of(int id, List<TaskDto> tasks) {
        return new Region(id, tasks);
    }

    private Region(int id, List<TaskDto> tasks) {
        this.id = id;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    List<AtmDto> orderAtmsByServicePriority() {
        tasks.sort(Comparator.comparingInt(task -> task.requestType().priority()));

        return tasks
                .stream()
                .map(task -> new AtmDto(this.id, task.atmId()))
                .distinct()
                .toList();
    }

}
