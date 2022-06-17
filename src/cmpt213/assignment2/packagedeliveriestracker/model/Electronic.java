package cmpt213.assignment2.packagedeliveriestracker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Electronic extends PackageSuper {
    private final double handleFee;

    public Electronic(String name, String notes, double price,
                double weight, LocalDateTime date, double handleFee) {
        super(name, notes, price, weight, date);
        this.isDelivered = false;
        this.handleFee = handleFee;
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
}
