package pl.simcode.ing.atmservice.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TaskDto(
        @NotNull
        @Min(1)
        @Max(9999)
        Integer region,

        @NotNull
        RequestType requestType,

        @NotNull
        @Min(1)
        @Max(9999)
        Integer atmId
) {
}
