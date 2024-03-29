package fabdev.spacexjavaapi.controllers;

import fabdev.spacexjavaapi.LaunchService;
import fabdev.spacexjavaapi.models.Astronaut;
import fabdev.spacexjavaapi.models.Launch;
import fabdev.spacexjavaapi.models.Rocket;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1")
public class LaunchController {

    private final LaunchService launchService;

    public LaunchController(LaunchService launchService) {
        this.launchService = launchService;
    }

    @GetMapping("launches")
    public List<Launch> getAllLaunches(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return launchService.getAllLaunches(page, limit);
    }

    @GetMapping("crew")
    public List<Astronaut> getAllCrew(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "9") int limit
    ) {
        return launchService.getAllCrew(page, limit);
    }

    @GetMapping("rockets")
    public List<Rocket> getAllRockets() {
        return launchService.getAllRockets();
    }
}
