package pl.simcode.ing.atmservice.api.dto;

import jakarta.validation.constraints.NotNull;

public record TaskDto(
        @NotNull
        Integer region,

        @NotNull
        RequestType requestType,

        @NotNull
        Integer atmId
) {
}
