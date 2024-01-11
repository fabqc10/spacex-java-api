package fabdev.spacexjavaapi.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ApiResponseDocsDTO(
        @JsonProperty("docs") List<ApiResponseDTO> launches
) {
}
