package fabdev.spacexjavaapi.models;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Rocket {
    private String id;
    private String name;
    private String type;
    private String company;
    private  String country;
    private String description;
    private Map<String, Object> height;
    private Map<String, Object> diameter;
    private Map<String, Object> mass;
    private List<String> images;

    public Rocket(String id, String name, String type, String company, String country, String description, Map<String, Object> height, Map<String, Object> diameter, Map<String, Object> mass, List<String> images) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.company = company;
        this.country = country;
        this.description = description;
        this.height = height;
        this.diameter = diameter;
        this.mass = mass;
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Map<String, Object> getDiameter() {
        return diameter;
    }

    public void setDiameter(Map<String, Object> diameter) {
        this.diameter = diameter;
    }

    public Map<String, Object> getMass() {
        return mass;
    }

    public void setMass(Map<String, Object> mass) {
        this.mass = mass;
    }

    public Map<String, Object> getHeight() {
        return height;
    }

    public void setHeight(Map<String, Object> height) {
        this.height = height;
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
