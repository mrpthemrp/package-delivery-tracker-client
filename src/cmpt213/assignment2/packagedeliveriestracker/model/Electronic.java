package cmpt213.assignment2.packagedeliveriestracker.model;

import java.time.LocalDateTime;

public class Electronic extends PackageBase {
    private double handleFee;

    public Electronic(String name, String notes, double price,
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
                "Package Type: Electronic\n" +
                "Notes: " + this.notes + "\n" +
                "Price (CAD): $" + this.price + "\n" +
                "Weight (kg): " + this.weight + "kg\n" +
                "Expected Delivery Date: " +
                this.expectedDeliveryDate.format(dateFormat) +
                "\nDelivery Status: " + deliveryStatus+ "\n" +
                "Environmental Handling Fee: "+ this.handleFee + "\n");
    }

    @Override
    protected void setExtraField(String field) {
        this.handleFee = Double.parseDouble(field);
    }
}
