package cmpt213.assignment1.packagedeliveriestracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that is modelled after a package.
 * @author Deborah Wang
 */
public class Package  {
    private final String name;
    private final String notes;
    private final double price;
    private final double weight;
    private boolean isDelivered;
    private final LocalDateTime expectedDeliveryDate;

    /**
     * Converts object's fields into String for printing.
     * @return A string of the object's fields, ready for print.
     */
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

    /**
     * Constructor for class, creates an object modelled as a package.
     * @param name The name of the package, cannot be empty.
     * @param notes Any notes for the package, can be empty.
     * @param price The price (in CAD) of the package, cannot be negative.
     * @param weight The weight (in kg) of the package, cannot be negative.
     * @param date The date of the package (Year, Month, Day, Hour, Minute), in 24-hour format.
     */
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

    /**
     * Changes a package's delivery status.
     * @param newStatus The new status of the package.
     */
    public void setDeliveryStatus(boolean newStatus) {
        this.isDelivered = newStatus;
    }

    /**
     * Gets the boolean value of the package's delivery status.
     * @return A boolean value.
     */
    public boolean getDeliveryStatus() {
        return isDelivered;
    }

    /**
     * Gets the delivery date of the package.
     * @return A LocalDateTime object.
     */
    public LocalDateTime getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    /**
     * Gets the name of the package.
     * @return A string that holds the name.
     */
    public String getName() {
        return this.name;
    }
}
