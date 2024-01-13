package fabdev.spacexjavaapi.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AstronautListDTO(
        List <AstronautDTO> crew
) {
}
