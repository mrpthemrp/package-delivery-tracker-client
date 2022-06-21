package cmpt213.assignment2.packagedeliveriestracker.model;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Package interface that ?
 *
 * @author Deborah Wang
 */
public interface Package extends Comparable<Package>{

    @Override
    int compareTo(Package p);

    boolean getDeliveryStatus();

    void setDeliveryStatus(boolean newStatus);

    LocalDateTime getExpectedDeliveryDate();

    String getName();
}
