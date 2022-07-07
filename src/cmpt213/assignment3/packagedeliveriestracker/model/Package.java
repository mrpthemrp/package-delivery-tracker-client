package cmpt213.assignment3.packagedeliveriestracker.model;

import java.time.LocalDateTime;

/**
 * Package interface that ?
 *
 * @author Deborah Wang
 */
public interface Package extends Comparable<PackageBase> {

    @Override
    int compareTo(PackageBase p);

    boolean getDeliveryStatus();

    void setDeliveryStatus(boolean newStatus);

    LocalDateTime getExpectedDeliveryDate();

    String getName();
}
