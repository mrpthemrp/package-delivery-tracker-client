package cmpt213.assignment2.packagedeliveriestracker.model;

import java.time.LocalDateTime;

public class PackageSuper implements Package{
    protected final String name;
    protected final String notes;
    protected final double price;
    protected final double weight;
    protected final LocalDateTime expectedDeliveryDate;
    protected boolean isDelivered;

    public PackageSuper(String name, String notes, double price, double weight, LocalDateTime expectedDeliveryDate) {
        this.name = name;
        this.notes = notes;
        this.price = price;
        this.weight = weight;
        this.expectedDeliveryDate = expectedDeliveryDate;

        this.isDelivered = false;
    }

    @Override
    public int compare(Package o1, Package o2) {
        if (o1.getExpectedDeliveryDate().isBefore(o2.getExpectedDeliveryDate())) {
            return -1;
        }
        return 0;
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
