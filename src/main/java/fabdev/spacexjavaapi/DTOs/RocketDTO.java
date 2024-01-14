package fabdev.spacexjavaapi.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
public record RocketDTO(
        String id,
        String name,
        String type,
        String company,
        String country,
        String description,
        @JsonProperty("height")
        Map<String, Object> height,
        @JsonProperty("diameter")
        Map<String, Object> diameter,
        @JsonProperty("mass")
        Map<String, Object> mass,
        @JsonProperty("flickr_images")
        List<String> images
) {
}
