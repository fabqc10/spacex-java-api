package fabdev.spacexjavaapi.models;

import java.time.LocalDate;
import java.util.List;

public class Launch {
    private int flight_number;
    private String name;
    private Rocket rocket;
    private LocalDate launchDate;
    private List<Payloads> customers;


    private boolean success;
    private boolean upcoming;

    public Launch(int flight_number,
                  String name,
                  Rocket rocket,
                  LocalDate launchDate,
                  List<Payloads> customers,
                  boolean success,
                  boolean upcoming) {
        this.flight_number = flight_number;
        this.name = name;
        this.rocket = rocket;
        this.launchDate = launchDate;
        this.customers = customers;
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

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
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

    public List<Payloads> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Payloads> customers) {
        this.customers = customers;
    }

}
