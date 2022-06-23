package cmpt213.assignment2.packagedeliveriestracker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class PackageBase implements Package {
    protected String type;
    protected final String name;
    protected final String notes;
    protected final double price;
    protected final double weight;
    protected final LocalDateTime expectedDeliveryDate;
    protected boolean isDelivered;

    protected static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");

    public PackageBase(String name, String notes, double price, double weight,
                       LocalDateTime expectedDeliveryDate) {
        this.name = name;
        this.notes = notes;
        this.price = price;
        this.weight = weight;
        this.expectedDeliveryDate = expectedDeliveryDate;

        this.isDelivered = false;
        this.type = "PackageBase";
    }

    @Override
    public int compareTo(Package p) {
        if (p.getExpectedDeliveryDate().isBefore(this.expectedDeliveryDate)) {
            return 1;
        }
        return -1;
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
