package fabdev.spacexjavaapi.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PatchDTO(
        @JsonProperty("small")
        String small,
        @JsonProperty("large")
        String large

) {
}
