package fabdev.spacexjavaapi.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LinksDTO(
        @JsonProperty("patch")
        PatchDTO patchDTO
) {
}
