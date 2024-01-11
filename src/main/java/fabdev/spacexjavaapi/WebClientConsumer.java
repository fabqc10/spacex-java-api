package fabdev.spacexjavaapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import fabdev.spacexjavaapi.DTOs.ApiResponseDocsDTO;
import fabdev.spacexjavaapi.mappers.RocketMapper;
import fabdev.spacexjavaapi.models.Launch;
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

    private final Logger logger = LoggerFactory.getLogger(WebClientConsumer.class);
    private final ObjectMapper objectMapper;
    private final WebClient webClient;

    public WebClientConsumer(ObjectMapper objectMapper, WebClient.Builder webClientBuilder) {
        this.objectMapper = objectMapper;
        this.webClient = webClientBuilder.baseUrl(LAUNCHES_URL).build();}


    public List<Launch> fetchAllLaunchesFromAPI() {
        String requestBody = "{\"query\": {}, \"options\": {\"pagination\": true, \"populate\": [{\"path\": \"rocket\", \"select\": {\"name\": 1 } }, {\"path\": \"payloads\", \"select\": {\"customers\": 1 } } ] } }";

        var dto = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(ApiResponseDocsDTO.class)
//                .map(this::mapToApiResponseList)
                .block();

        logger.info("dto response: {}",dto);
        logger.info("Api response: {}",requestBody);

        return dto.launches().stream()
                .map(launchApiResponse -> {
                    var launch= new Launch(launchApiResponse.flight_number(),
                            launchApiResponse.name(),
                            RocketMapper.mapRocketDTOToRocket(launchApiResponse.rocket()),
                            launchApiResponse.launchDate(),
                            launchApiResponse.success(),
                            launchApiResponse.upcoming()
                            );
                    return launch;
                }).toList();
    }

}
