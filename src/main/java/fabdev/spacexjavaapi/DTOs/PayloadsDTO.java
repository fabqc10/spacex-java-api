package fabdev.spacexjavaapi.DTOs;

import java.util.List;

public record PayloadsDTO(
        List<String> customers,
        String id
) {
}
