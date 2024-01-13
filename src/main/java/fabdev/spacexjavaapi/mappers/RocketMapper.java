package fabdev.spacexjavaapi.mappers;

import fabdev.spacexjavaapi.DTOs.RocketDTO;
import fabdev.spacexjavaapi.models.Rocket;

public class RocketMapper {
    public static Rocket mapRocketDTOToRocket(RocketDTO rocketDTO){
        return new Rocket(
                rocketDTO.id(),
                rocketDTO.name(),
                rocketDTO.type(),
                rocketDTO.company(),
                rocketDTO.country(),
                rocketDTO.description());
    }
}
