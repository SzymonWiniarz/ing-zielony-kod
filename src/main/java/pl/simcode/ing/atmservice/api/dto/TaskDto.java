package pl.simcode.ing.atmservice.api.dto;

import pl.simcode.ing.exceptions.ValidationException;

public record TaskDto(
        Integer region,

        RequestType requestType,

        Integer atmId
) {

    public TaskDto {
        if (region == null || region < 1 || region > 9999) {
            throw new ValidationException(TaskDto.class, "region", region);
        }

        if (requestType == null) {
            throw new ValidationException(TaskDto.class, "requestType", null);
        }

        if (atmId == null || atmId < 1 || atmId > 9999) {
            throw new ValidationException(TaskDto.class, "atmId", atmId);
        }
    }
}
