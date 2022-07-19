package cmpt213.assignment3.packagedeliveries.model;

import java.time.LocalDateTime;

public class PackageFactory {
    public PackageBase getInstance(PackageType packageType, String name, String notes,
                                   double price, double weight, LocalDateTime deliveryDate, String extraField) {

        if (packageType == null) {
            return null;
        }
        return switch (packageType) {
            case BOOK -> new Book(name, notes, price, weight, deliveryDate, extraField);
            case PERISHABLE ->
                    new Perishable(name, notes, price, weight, deliveryDate, LocalDateTime.parse(extraField));
            case ELECTRONIC -> new Electronic(name, notes, price, weight, deliveryDate, Double.parseDouble(extraField));
        };
    }

    public enum PackageType {
        BOOK, PERISHABLE, ELECTRONIC
    }


}