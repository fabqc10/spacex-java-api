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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;

@Component
public class WebClientConsumer {
    @Value("${spacex.api.launches-url}")
    private String LAUNCHES_URL;
    @Value("${spacex.api.crew-url}")
    private String CREW_URL;
    @Value("${spacex.api.rockets-url}")
    private String ROCKETS_URL;

    private final Logger logger = LoggerFactory.getLogger(WebClientConsumer.class);
    private final ObjectMapper objectMapper;
    private final WebClient webClient;

    public WebClientConsumer(ObjectMapper objectMapper, WebClient webClient) {
        this.objectMapper = objectMapper;
        this.webClient = webClient;
    }


    public List<Launch> fetchAllLaunchesFromAPI(int page, int limit) {
        try {
            String requestBody = "{\"query\": {}, \"options\": {\"pagination\": true, \"populate\": [{\"path\": \"rocket\", \"select\": {\"name\": 1, \"type\": 1, \"country\": 1, \"company\": 1, \"description\": 1, \"height\": 1 , \"diameter\": 1, \"mass\": 1 , \"flickr_images\": 1} }, {\"path\": \"payloads\", \"select\": {\"customers\": 1 } } ], \"page\": " + page + ", \"limit\": " + limit + " } }";

            var dto = webClient.post()
                    .uri(LAUNCHES_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(requestBody))
                    .retrieve()
                    .bodyToMono(ApiResponseDocsDTO.class)
                    .block();

            if (dto != null) {
                logger.info("dto response: {}", dto);
                logger.info("Api response: {}", requestBody);

                return dto.launches().stream()
                        .map(LaunchMapper::mapApiResponseDTOToLaunch)
                        .toList();
            } else {
                throw new IllegalStateException("Failed to fetch launches from the API.");
            }
        } catch (WebClientResponseException e) {
            logger.error("API error: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Failed to fetch launches from the API.", e);
        }
    }


    public List<Astronaut> getAllCrewFromApi(int page, int limit) {
        try {
            String requestBody = "{\"query\": {}, \"options\": {\"pagination\": true, \"page\": " + page + ", \"limit\": " + limit + " } }";

            var dto = webClient.post()
                    .uri(CREW_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(requestBody))
                    .retrieve()
                    .bodyToMono(ApiResponseDocsCrewDTO.class)
                    .block();

            if (dto != null) {
                logger.info("dto crew response: {}", dto);

                return dto.crew().stream()
                        .map(CrewMapper::mapAstronautDTOToAstronaut)
                        .toList();
            } else {
                throw new IllegalStateException("Failed to fetch Astronauts from the API.");
            }

        } catch (WebClientResponseException e) {
            logger.error("API error: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Failed to fetch Astronauts from the API.", e);
        }

    }

    public List<Rocket> getAllRocketsFromApi() {
        try {
            return webClient.get()
                    .uri(ROCKETS_URL)
                    .retrieve()
                    .bodyToFlux(RocketDTO.class)
                    .map(RocketMapper::mapRocketDTOToRocket)
                    .collectList()
                    .block();
        } catch (WebClientResponseException e) {
            logger.error("API error: {}", e.getResponseBodyAsString());
            throw new RuntimeException("Failed to fetch Rockets from the API.", e);
        }

    }


}

//    public Mono<List<Launch>> fetchAllLaunchesFromAPI(int page, int limit) {
//        // ... (your WebClient configuration)
//        return webClient.post()
//                .uri(launchesUrl)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(requestBody))
//                .retrieve()
//                .onStatus(HttpStatus::isError, response -> Mono.error(new YourCustomException("WebClient request failed")))
//                .bodyToMono(ApiResponseDocsDTO.class)
//                .map(dto -> dto.launches().stream()
//                        .map(LaunchMapper::mapApiResponseDTOToLaunch)
//                        .toList());
//    }

