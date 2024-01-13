package fabdev.spacexjavaapi.mappers;

import fabdev.spacexjavaapi.DTOs.AstronautDTO;
import fabdev.spacexjavaapi.models.Astronaut;

public class CrewMapper {
    public static Astronaut mapAstronautDTOToAstronaut(AstronautDTO astronautDTO){
        return new Astronaut(
                astronautDTO.id(),
                astronautDTO.name(),
                astronautDTO.agency(),
                astronautDTO.image(),
                astronautDTO.wikipedia(),
                astronautDTO.status()
        );
    }
}
