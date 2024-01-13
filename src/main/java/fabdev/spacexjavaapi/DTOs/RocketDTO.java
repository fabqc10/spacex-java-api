package fabdev.spacexjavaapi.DTOs;

import java.util.UUID;

public record RocketDTO(
        String id,
        String name,
        String type,
        String company,
        String country,
        String description
) {
}
