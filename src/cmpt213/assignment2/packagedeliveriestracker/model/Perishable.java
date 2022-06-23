package cmpt213.assignment2.packagedeliveriestracker.model;

import java.time.LocalDateTime;

public class Perishable extends PackageBase {
    private final LocalDateTime expiryDate;

    public Perishable(String name, String notes, double price,
                      double weight, LocalDateTime deliveryDate, LocalDateTime expiryDate) {

        super(name, notes, price, weight, deliveryDate);
        this.expiryDate = expiryDate;
        this.type = "Perishable";
    }

    @Override
    public String toString() {

        String deliveryStatus = "Not delivered.";

        if (this.isDelivered) {
            deliveryStatus = "Delivered.";
        }

        return ("Package Name: " + this.name + "\n" +
                "Package Type: Perishable\n" +
                "Notes: " + this.notes + "\n" +
                "Price (CAD): $" + this.price + "\n" +
                "Weight (kg): " + this.weight + "kg\n" +
                "Expected Delivery Date: " +
                this.expectedDeliveryDate.format(dateFormat) +
                "\nDelivery Status: " + deliveryStatus + "\n" +
                "Product Expiry Date: " + this.expiryDate.format(dateFormat) + "\n");
    }

}
