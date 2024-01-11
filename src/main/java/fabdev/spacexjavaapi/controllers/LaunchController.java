package fabdev.spacexjavaapi.controllers;

import fabdev.spacexjavaapi.DTOs.ApiResponseDTO;
import fabdev.spacexjavaapi.LaunchService;
import fabdev.spacexjavaapi.models.Launch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("api/v1/launches")
public class LaunchController {

   private final LaunchService launchService;

    public LaunchController(LaunchService launchService) {
        this.launchService = launchService;
    }

    @GetMapping()
    public List<Launch> getAllLaunches() {
        return launchService.getAllLaunches();
    }
}
