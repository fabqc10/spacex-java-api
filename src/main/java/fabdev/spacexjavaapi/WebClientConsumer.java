package fabdev.spacexjavaapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import fabdev.spacexjavaapi.DTOs.ApiResponseDocsCrewDTO;
import fabdev.spacexjavaapi.DTOs.ApiResponseDocsDTO;
import fabdev.spacexjavaapi.DTOs.RocketDTO;
import fabdev.spacexjavaapi.mappers.CrewMapper;
import fabdev.spacexjavaapi.mappers.LaunchMapper;
import fabdev.spacexjavaapi.mappers.RocketMapper;
import fabdev.spacexjavaapi.models.Astronaut;
import fabdev.spacexjavaapi.models.Launch;
import fabdev.spacexjavaapi.models.Rocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Component
public class WebClientConsumer {
    private static final  String LAUNCHES_URL= "https://api.spacexdata.com/v4/launches/query";
    private static final String CREW_URL="https://api.spacexdata.com/v4/crew/query";
    private static final String ROCKETS_URL = "https://api.spacexdata.com/v4/rockets";

    private final Logger logger = LoggerFactory.getLogger(WebClientConsumer.class);
    private final ObjectMapper objectMapper;
    private final WebClient webClient;

    public WebClientConsumer(ObjectMapper objectMapper, WebClient webClient) {
        this.objectMapper = objectMapper;
        this.webClient = webClient;}


    public List<Launch> fetchAllLaunchesFromAPI(int page, int limit) {
        String requestBody = "{\"query\": {}, \"options\": {\"pagination\": true, \"populate\": [{\"path\": \"rocket\", \"select\": {\"name\": 1, \"type\": 1, \"country\": 1, \"company\": 1, \"description\": 1, \"height\": 1 , \"diameter\": 1, \"mass\": 1 , \"flickr_images\": 1} }, {\"path\": \"payloads\", \"select\": {\"customers\": 1 } } ], \"page\": " + page + ", \"limit\": " + limit + " } }";

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
                .map(LaunchMapper::mapApiResponseDTOToLaunch)
                .toList();
    }

    public List<Astronaut> getAllCrewFromApi(int page, int limit){
        String requestBody = "{\"query\": {}, \"options\": {\"pagination\": true, \"page\": " + page + ", \"limit\": " + limit + " } }";

        var dto = webClient.post()
                .uri(CREW_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(ApiResponseDocsCrewDTO.class)
                .block();


        logger.info("dto crew response: {}",dto);

        return dto.crew().stream()
                .map(CrewMapper::mapAstronautDTOToAstronaut)
                .toList();
    }

    public List<Rocket> getAllRocketsFromApi(){
        var dto = webClient.get()
                .uri(ROCKETS_URL)
                .retrieve()
                .bodyToFlux(RocketDTO.class)
                .map(RocketMapper::mapRocketDTOToRocket)
                .collectList()
                .block();
        return dto;
    }


}
