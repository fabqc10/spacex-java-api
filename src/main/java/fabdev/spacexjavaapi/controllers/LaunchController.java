package fabdev.spacexjavaapi.controllers;

import fabdev.spacexjavaapi.DTOs.ApiResponseDTO;
import fabdev.spacexjavaapi.LaunchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/launches")
public class LaunchController {

   private final LaunchService launchService;

    public LaunchController(LaunchService launchService) {
        this.launchService = launchService;
    }

    @GetMapping()
    public Flux<ApiResponseDTO> getAllLaunches() {
        return launchService.getAllLaunches();
    }
}
