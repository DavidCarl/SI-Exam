package entities;

public class Car {
    private String brand;
    private String model;
    private String description;
    private String branch;

    public Car(String brand, String model, String description, String branch) {
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.branch = branch;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return description;
    }
}
