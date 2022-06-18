package cmpt213.assignment2.packagedeliveriestracker.model;

import java.time.LocalDateTime;

public class Perishable extends PackageBase {
    private LocalDateTime expiryDate;

    public Perishable(String name, String notes, double price,
                double weight, LocalDateTime deliveryDate, String extraField) {
        super(name, notes, price, weight, deliveryDate, extraField);
        setExtraField(extraField);
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
                "\nDelivery Status: " + deliveryStatus+ "\n" +
                "Product Expiry Date: "+ this.expiryDate.format(dateFormat) + "\n");
    }

    @Override
    protected void setExtraField(String field) {
        int[] tempArr = {0,0,0,0,0};

        String[] temp = field.split(",");

        for (int i =0; i < temp.length; i++){
            tempArr[i] = Integer.parseInt(temp[i]);
        }

        this.expiryDate = LocalDateTime.of(tempArr[0], tempArr[1], tempArr[2], tempArr[3],tempArr[4]);
    }
}
