package fabdev.spacexjavaapi.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ApiResponseDocsCrewDTO(
        @JsonProperty("docs")
        List<AstronautDTO> crew
) {
}
