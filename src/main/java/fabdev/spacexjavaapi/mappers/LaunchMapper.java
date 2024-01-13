package fabdev.spacexjavaapi.mappers;

import fabdev.spacexjavaapi.DTOs.ApiResponseDTO;
import fabdev.spacexjavaapi.models.Launch;

public class LaunchMapper {
    public static Launch mapApiResponseDTOToLaunch(ApiResponseDTO apiResponseDTO){
        return new Launch(
                apiResponseDTO.flight_number(),
                apiResponseDTO.name(),
                RocketMapper.mapRocketDTOToRocket(apiResponseDTO.rocket()),
                apiResponseDTO.launchDate(),
                PayloadsMapper.mapPayloadsDTOToPayloads(apiResponseDTO.payloads()),
                apiResponseDTO.links(),
                apiResponseDTO.details(),
                apiResponseDTO.success(),
                apiResponseDTO.upcoming()
        );
    }
}
