package fabdev.spacexjavaapi.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fabdev.spacexjavaapi.CustomLocalDateDeserializer;

import java.time.LocalDate;
import java.util.List;

public record ApiResponseDTO(
                            @JsonProperty("flight_number")
                            int flight_number,
                             @JsonProperty("name")
                             String name,
                             @JsonProperty("rocket")
                             RocketDTO rocket,
                             @JsonProperty("date_local")
                             @JsonDeserialize(using = CustomLocalDateDeserializer.class)
                             LocalDate launchDate,
                             @JsonProperty("payloads")
                             List<PayloadsDTO> payloads,
                            @JsonProperty("links")
                             LinksDTO links,
                             @JsonProperty("details")
                             String details,
                             boolean success,
                             boolean upcoming) {

}
