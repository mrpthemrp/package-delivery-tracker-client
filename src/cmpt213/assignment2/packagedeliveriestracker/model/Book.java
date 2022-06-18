package cmpt213.assignment2.packagedeliveriestracker.model;

import java.time.LocalDateTime;

public class Book extends PackageBase {

    private String authorName;

    public Book(String name, String notes, double price,
                   double weight, LocalDateTime date, String extraField) {
        super(name, notes, price, weight, date, extraField);
        setExtraField(extraField);
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
                "Author name: "+ this.authorName + "\n");
    }

    public String getAuthor(){
        return this.authorName;
    }

    @Override
    protected void setExtraField(String field) {
        this.authorName = field;
    }
}
