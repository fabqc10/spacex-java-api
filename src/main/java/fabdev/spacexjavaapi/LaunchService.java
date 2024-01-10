package fabdev.spacexjavaapi;

import fabdev.spacexjavaapi.DTOs.ApiResponseDTO;
import fabdev.spacexjavaapi.models.Launch;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LaunchService {
    private final WebClientConsumer webClientConsumer;

    public LaunchService(WebClientConsumer webClientConsumer) {
        this.webClientConsumer = webClientConsumer;
    }

    public Flux<ApiResponseDTO> getAllLaunches() {
        return webClientConsumer.fetchAllLaunchesFromAPI();
    }
}
