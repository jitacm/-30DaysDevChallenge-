import java.io.Serializable;

public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String make;
    private String model;
    private int year;
    private double fuelEfficiency; // in km/l
    private double expenses; // in $

    public Vehicle(String make, String model, int year, double fuelEfficiency, double expenses) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelEfficiency = fuelEfficiency;
        this.expenses = expenses;
    }

    // Getters and setters
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getFuelEfficiency() { return fuelEfficiency; }
    public void setFuelEfficiency(double fuelEfficiency) { this.fuelEfficiency = fuelEfficiency; }
    public double getExpenses() { return expenses; }
    public void setExpenses(double expenses) { this.expenses = expenses; }
}
