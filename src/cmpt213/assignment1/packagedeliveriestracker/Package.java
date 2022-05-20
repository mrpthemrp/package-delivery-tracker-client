package cmpt213.assignment1.packagedeliveriestracker;

import java.time.LocalDateTime;

public class Package {
    private String name;
    private String notes;
    private double price;
    private double weight;
    private boolean isDelivered;
    private LocalDateTime expectedDeliveryDate;

    public Package (String name, String notes, double price,
                    double weight, LocalDateTime date){
        this.isDelivered = false;

        this.name = name;
        this.notes = notes;
        this.price = price;
        this.weight = weight;
        this.expectedDeliveryDate = date;
    }

    //GETTERS
    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public LocalDateTime getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    //toString things

}
