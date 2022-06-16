package cmpt213.assignment2.packagedeliveriestracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Electronic implements Package{
    private final String name;
    private final String notes;
    private final double price;
    private final double handleFee;
    private final double weight;
    private final LocalDateTime expectedDeliveryDate;
    private boolean isDelivered;

    public Electronic(String name, String notes, double price,
                double weight, LocalDateTime date, double handleFee) {
        this.isDelivered = false;

        this.name = name;
        this.notes = notes;
        this.price = price;
        this.handleFee = handleFee;
        this.weight = weight;
        this.expectedDeliveryDate = date;
    }

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
                "Expected Delivery Date: " +
                this.expectedDeliveryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a")) +
                "\nDelivery Status: " + deliveryStatus);
    }

    @Override
    public boolean getDeliveryStatus() {
        return this.isDelivered;
    }

    @Override
    public void setDeliveryStatus(boolean newStatus) {
        this.isDelivered = newStatus;
    }

    @Override
    public LocalDateTime getExpectedDeliveryDate() {
        return this.expectedDeliveryDate;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
