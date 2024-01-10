package fabdev.spacexjavaapi;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateString = p.getValueAsString();

        // Parse ZonedDateTime from the input string
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString);

        // Extract LocalDate from ZonedDateTime
        return zonedDateTime.toLocalDate();
    }
}
