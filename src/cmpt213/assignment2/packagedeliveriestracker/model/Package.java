package cmpt213.assignment2.packagedeliveriestracker.model;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Package class that is modelled after a package; stores
 * name, notes, price, weight, date, and delivery status.
 *
 * @author Deborah Wang
 */
public interface Package extends Comparator<Package> {
    @Override
    int compare(Package o1, Package o2);
    @Override
    String toString();

    boolean getDeliveryStatus();

    void setDeliveryStatus(boolean newStatus);

    LocalDateTime getExpectedDeliveryDate();

    String getName();
}
