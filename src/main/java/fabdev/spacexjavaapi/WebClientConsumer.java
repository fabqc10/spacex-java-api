package fabdev.spacexjavaapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import fabdev.spacexjavaapi.DTOs.ApiResponseDocsDTO;
import fabdev.spacexjavaapi.DTOs.AstronautDTO;
import fabdev.spacexjavaapi.DTOs.AstronautListDTO;
import fabdev.spacexjavaapi.mappers.CrewMapper;
import fabdev.spacexjavaapi.mappers.PayloadsMapper;
import fabdev.spacexjavaapi.mappers.RocketMapper;
import fabdev.spacexjavaapi.models.Astronaut;
import fabdev.spacexjavaapi.models.Launch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class WebClientConsumer {
    //test
    private static final  String LAUNCHES_URL= "https://api.spacexdata.com/v4/launches/query";
    private static final String CREW_URL="https://api.spacexdata.com/v4/crew";

    private final Logger logger = LoggerFactory.getLogger(WebClientConsumer.class);
    private final ObjectMapper objectMapper;
    private final WebClient webClient;

    public WebClientConsumer(ObjectMapper objectMapper, WebClient webClient) {
        this.objectMapper = objectMapper;
        this.webClient = webClient;}


    public List<Launch> fetchAllLaunchesFromAPI() {
        String requestBody = "{\"query\": {}, \"options\": {\"pagination\": true, \"populate\": [{\"path\": \"rocket\", \"select\": {\"name\": 1 } }, {\"path\": \"payloads\", \"select\": {\"customers\": 1 } } ] } }";

        var dto = webClient.post()
                .uri(LAUNCHES_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(ApiResponseDocsDTO.class)
                .block();

        logger.info("dto response: {}",dto);
        logger.info("Api response: {}",requestBody);

        return dto.launches().stream()
                .map(launchApiResponse -> {
                    var launch= new Launch(launchApiResponse.flight_number(),
                            launchApiResponse.name(),
                            RocketMapper.mapRocketDTOToRocket(launchApiResponse.rocket()),
                            launchApiResponse.launchDate(),
                            PayloadsMapper.mapPayloadsDTOToPayloads(launchApiResponse.payloads()),
                            launchApiResponse.links(),
                            launchApiResponse.details(),
                            launchApiResponse.success(),
                            launchApiResponse.upcoming()
                            );
                    return launch;
                }).toList();
    }

    public List<Astronaut> getAllCrewFromApi(){
        var dto = webClient.get()
                .uri(CREW_URL)
                .retrieve()
                .bodyToFlux(AstronautDTO.class)
                .collectList()
                .block();


        logger.info("dto crew response: {}",dto);

        return dto.stream()
                .map(CrewMapper::mapAstronautDTOToAstronaut)
                .toList();
    }



}
