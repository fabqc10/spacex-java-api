package fabdev.spacexjavaapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fabdev.spacexjavaapi.DTOs.ApiResponseDTO;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.*;

@Component
public class WebClientConsumer {
    private static final  String launches_url= "https://api.spacexdata.com/v4/launches/query";
    public Flux<ApiResponseDTO> fetchAllLaunchesFromAPI(){

        // Create the object structure as a Map
        Map<String, Object> requestBody = new LinkedHashMap<>();
        Map<String, Object> query = new LinkedHashMap<>();
        Map<String, Object> options = new LinkedHashMap<>();
        List<Object> populate = new ArrayList<>();
        Map<String, Object> rocket = new LinkedHashMap<>();
        Map<String, Object> rocketSelect = new LinkedHashMap<>();
        Map<String, Object> payloads = new LinkedHashMap<>();
        Map<String, Object> payloadsSelect = new LinkedHashMap<>();

        rocketSelect.put("name", 1);
        rocket.put("path", "rocket");
        rocket.put("select", rocketSelect);

        payloadsSelect.put("customers", 1);
        payloads.put("path", "payloads");
        payloads.put("select", payloadsSelect);

        populate.add(rocket);
        populate.add(payloads);

        options.put("pagination", false);
        options.put("populate", populate);

        requestBody.put("query", query);
        requestBody.put("options", options);

        Flux<ApiResponseDTO> launches = WebClient.create()
                .post()
                .uri(launches_url)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToFlux(DataBuffer.class) // Read response as DataBuffer
                .map(dataBuffer -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        return mapper.readValue(dataBuffer.asInputStream(), ApiResponseDTO.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to read data buffer", e);
                    }
                })
                .onErrorResume(e -> Flux.empty()); // Handle errors gracefully
        return launches;
    }



}
