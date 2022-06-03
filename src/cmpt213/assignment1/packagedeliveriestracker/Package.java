package cmpt213.assignment1.packagedeliveriestracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Package  {
    private final String name;
    private final String notes;
    private final double price;
    private final double weight;
    private boolean isDelivered;
    private final LocalDateTime expectedDeliveryDate;

    @Override
    public String toString() {
        String deliveryStatus = "Not delivered.";
        if (this.isDelivered) {
            deliveryStatus = "Delivered.";
        }

        return ("Package Name: " + this.name + "\n" +
                "Notes: " + this.notes + "\n" +
                "Price (CAD): $" + this.price + "\n" +
                "Weight (kg): " + this.weight + "kg\n" +
                "Expected Delivery Date: " + this.expectedDeliveryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a")) + "\n" +
                "Delivery Status: " + deliveryStatus);
    }

    public Package(String name, String notes, double price,
                   double weight, LocalDateTime date) {
        this.isDelivered = false;

        this.name = name;
        this.notes = notes;
        this.price = price;
        this.weight = weight;
        this.expectedDeliveryDate = date;
    }

    //GETTERS, SETTER
    public void setDeliveryStatus(boolean newStatus) {
        this.isDelivered = newStatus;
    }

    public boolean getDeliveryStatus() {
        return isDelivered;
    }

    public LocalDateTime getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }


}
