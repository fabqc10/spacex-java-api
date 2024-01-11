package fabdev.spacexjavaapi.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PayloadsDTO(
        @JsonProperty("customers")
        List<String> customers,
        @JsonProperty("id")
        String id
) {
}
