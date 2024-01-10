package fabdev.spacexjavaapi.models;

import java.time.LocalDate;
import java.util.List;

public class Launch {
    private int flight_number;
    private String name;
    private String rocket;
    private LocalDate launchDate;
    private boolean success;
    private boolean upcoming;

    public Launch(int flight_number, String name, String rocket, LocalDate launchDate, boolean success, boolean upcoming) {
        this.flight_number = flight_number;
        this.name = name;
        this.rocket = rocket;
        this.launchDate = launchDate;
        this.success = success;
        this.upcoming = upcoming;
    }

    public int getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(int flight_number) {
        this.flight_number = flight_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRocket() {
        return rocket;
    }

    public void setRocket(String rocket) {
        this.rocket = rocket;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isUpcoming() {
        return upcoming;
    }

    public void setUpcoming(boolean upcoming) {
        this.upcoming = upcoming;
    }

}
