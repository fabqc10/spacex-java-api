package fabdev.spacexjavaapi;

import fabdev.spacexjavaapi.DTOs.ApiResponseDTO;
import fabdev.spacexjavaapi.models.Astronaut;
import fabdev.spacexjavaapi.models.Launch;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class LaunchService {
    private final WebClientConsumer webClientConsumer;

    public LaunchService(WebClientConsumer webClientConsumer) {
        this.webClientConsumer = webClientConsumer;
    }

    public List<Launch> getAllLaunches(int page, int limit) {
        return webClientConsumer.fetchAllLaunchesFromAPI(page, limit);
    }

    public List<Astronaut> getAllCrew(int page, int limit){
        return webClientConsumer.getAllCrewFromApi(page,limit);
    }

}
