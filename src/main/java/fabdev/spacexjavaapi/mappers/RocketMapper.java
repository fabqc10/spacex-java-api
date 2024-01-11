package fabdev.spacexjavaapi.mappers;

import fabdev.spacexjavaapi.DTOs.RocketDTO;
import fabdev.spacexjavaapi.models.Rocket;

public class RocketMapper {
    public static Rocket mapRocketDTOToRocket(RocketDTO rocketDTO){
        return new Rocket(rocketDTO.name(), rocketDTO.id());
    }
}
