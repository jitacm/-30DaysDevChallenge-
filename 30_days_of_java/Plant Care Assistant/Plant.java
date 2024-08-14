import java.io.Serializable;
import java.time.LocalDate;

public class Plant implements Serializable {
    private String name;
    private String species;
    private LocalDate lastWatered;
    private LocalDate lastFertilized;
    private String healthStatus;

    public Plant(String name, String species) {
        this.name = name;
        this.species = species;
        this.lastWatered = LocalDate.now();
        this.lastFertilized = LocalDate.now();
        this.healthStatus = "Good";
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public LocalDate getLastWatered() { return lastWatered; }
    public void setLastWatered(LocalDate lastWatered) { this.lastWatered = lastWatered; }
    public LocalDate getLastFertilized() { return lastFertilized; }
    public void setLastFertilized(LocalDate lastFertilized) { this.lastFertilized = lastFertilized; }
    public String getHealthStatus() { return healthStatus; }
    public void setHealthStatus(String healthStatus) { this.healthStatus = healthStatus; }

    @Override
    public String toString() {
        return name + " (" + species + ")";
    }
}