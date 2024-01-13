package fabdev.spacexjavaapi.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AstronautDTO(
        @JsonProperty("id")
        String id,
        @JsonProperty("name")
        String name,
        @JsonProperty("agency")
        String agency,
        @JsonProperty("image")
        String image,
        @JsonProperty("wikipedia")
        String wikipedia,
        @JsonProperty("status")
        String status

) {
}
