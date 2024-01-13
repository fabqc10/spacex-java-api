package fabdev.spacexjavaapi.models;

import java.util.UUID;

public class Rocket {
    private String id;
    private String name;
    private String type;
    private String company;
    private  String country;
    private String description;

    public Rocket(String id, String name, String type, String company, String country, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.company = company;
        this.country = country;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
