package pl.simcode.ing.atmservice;

import pl.simcode.ing.atmservice.api.dto.TaskDto;
import pl.simcode.ing.atmservice.api.dto.AtmDto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

class TasksBundle {

    private final List<Region> regions;

    static TasksBundle of(List<TaskDto> taskDtos) {
        Map<Integer, List<TaskDto>> tasksByRegion = taskDtos
                .stream()
                .collect(groupingBy(TaskDto::region, toList()));

        List<Region> regions = tasksByRegion.entrySet()
                .stream()
                .map(taskEntry -> Region.of(taskEntry.getKey(), taskEntry.getValue()))
                .collect(toList());

        return new TasksBundle(regions);
    }

    private TasksBundle(List<Region> regions) {
        this.regions = regions;
    }

    List<AtmDto> createServicePlan() {
        regions.sort(Comparator.comparing(Region::getId));

        return regions
                .stream()
                .flatMap(region -> region.orderAtmsByServicePriority().stream())
                .toList();
    }

}
