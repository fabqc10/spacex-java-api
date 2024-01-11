package fabdev.spacexjavaapi.models;

import java.util.List;

public class Payloads {
    private List<String> customers;
    private String id;

    public Payloads(List<String> customers, String id) {
        this.customers = customers;
        this.id = id;
    }

    public List<String> getCustomers() {
        return customers;
    }

    public void setCustomers(List<String> customers) {
        this.customers = customers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
