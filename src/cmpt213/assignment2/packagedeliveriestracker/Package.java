package cmpt213.assignment2.packagedeliveriestracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Package class that is modelled after a package; stores
 * name, notes, price, weight, date, and delivery status.
 *
 * @author Deborah Wang
 */
public class Package {
    private final String name;
    private final String notes;
    private final double price;
    private final double weight;
    private final LocalDateTime expectedDeliveryDate;
    private boolean isDelivered;

    /**
     * Constructor for class, creates an object modelled as a package
     * with its respective fields.
     *
     * @param name   The name of the package, cannot be empty.
     * @param notes  Any notes for the package, can be empty.
     * @param price  The price (in CAD) of the package, cannot be negative.
     * @param weight The weight (in kg) of the package, cannot be negative.
     * @param date   The date of the package (Year, Month, Day, Hour, Minute), in 24-hour format.
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

    /**
     * Converts object's fields into a String that
     * conveys what the fields are (units are included).
     *
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
                "Expected Delivery Date: " +
                this.expectedDeliveryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a")) +
                "\nDelivery Status: " + deliveryStatus);
    }

    /**
     * Gets the package's delivery status;
     * returns false if not delivered, true if delivered.
     *
     * @return A boolean value (true or false);
     */
    public boolean getDeliveryStatus() {
        return this.isDelivered;
    }

    /**
     * Changes a package's delivery status;
     * either true or false.
     *
     * @param newStatus The new status of the package, bool value.
     */
    public void setDeliveryStatus(boolean newStatus) {
        this.isDelivered = newStatus;
    }

    /**
     * Gets the delivery date of the package
     * as a LocalDateTime object.
     *
     * @return A LocalDateTime object with the date information.
     */
    public LocalDateTime getExpectedDeliveryDate() {
        return this.expectedDeliveryDate;
    }

    /**
     * Gets the name of the package.
     *
     * @return A String that holds the name.
     */
    public String getName() {
        return this.name;
    }
}
