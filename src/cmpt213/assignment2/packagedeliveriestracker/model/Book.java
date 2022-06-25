package cmpt213.assignment2.packagedeliveriestracker.model;

import java.time.LocalDateTime;

public class Book extends PackageBase {
    private final String authorName;

    public Book(String name, String notes, double price,
                double weight, LocalDateTime date, String authorName) {

        super(name, notes, price, weight, date);
        this.authorName = authorName;
    }

    @Override
    public String toString() {

        String deliveryStatus = "Not delivered.";

        if (this.isDelivered) {
            deliveryStatus = "Delivered.";
        }

        return ("Package Name: " + this.name + "\n" +
                "Package Type: Book\n" +
                "Notes: " + this.notes + "\n" +
                "Price (CAD): $" + this.price + "\n" +
                "Weight (kg): " + this.weight + "kg\n" +
                "Expected Delivery Date: " +
                this.expectedDeliveryDate.format(dateFormat) +
                "\nDelivery Status: " + deliveryStatus + "\n" +
                "Author name: " + this.authorName + "\n");
    }

}
