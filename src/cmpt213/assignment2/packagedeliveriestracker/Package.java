package cmpt213.assignment2.packagedeliveriestracker;

import java.time.LocalDateTime;

/**
 * Package class that is modelled after a package; stores
 * name, notes, price, weight, date, and delivery status.
 *
 * @author Deborah Wang
 */
public interface Package {
    @Override
    String toString();

    boolean getDeliveryStatus();

    void setDeliveryStatus(boolean newStatus);

    LocalDateTime getExpectedDeliveryDate();

    String getName();
}
