package fabdev.spacexjavaapi.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fabdev.spacexjavaapi.CustomLocalDateDeserializer;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public record ApiResponseDTO(int flight_number,
                             String name,
                             String rocket,
                             @JsonProperty("date_local")
                             @JsonDeserialize(using = CustomLocalDateDeserializer.class)
                             LocalDate launchDate,
                             boolean success,
                             boolean upcoming) {

}
