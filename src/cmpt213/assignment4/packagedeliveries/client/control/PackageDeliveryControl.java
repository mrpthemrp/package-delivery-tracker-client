package cmpt213.assignment4.packagedeliveries.client.control;

import cmpt213.assignment4.packagedeliveries.client.view.util.Util;
import cmpt213.assignment4.packagedeliveries.client.model.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class creates a PackageDeliveryControl object which manages data for the program.
 * @author Deborah Wang
 */
public class PackageDeliveryControl {

//    private final HttpURLConnection server;
    public final static int DATA_SAVE = 1;
    private final static int DATA_LOAD = 2;
    public final static int REMOVE = 1;
    public final static int DELIVERY_STATUS = 2;
    private static ArrayList<PackageBase> masterListOfPackages;
    private static ArrayList<PackageBase> upcomingPackages;
    private static ArrayList<PackageBase> overduePackages;
    private final PackageFactory pkgFactory;

    /**
     * Constructs a PackageDeliveryControl Object.
     * Initializes class fields and also loads in any data from the JSON list.
     */
    public PackageDeliveryControl() {
//        this.server = new
        this.pkgFactory = new PackageFactory();

        masterListOfPackages = new ArrayList<>();
        upcomingPackages = new ArrayList<>();
        overduePackages = new ArrayList<>();
    }

    /**
     * Method that creates a new Package using the PackageFactory class.
     * Updates lists after package creation.
     * @param name Name of the package.
     * @param notes Any notes for the package.
     * @param price Price of the package.
     * @param weight Weight of the package.
     * @param date Expected Delivery Date of the package.
     * @param extraField Extra field of the package, in String form already.
     * @param type Subclass type of the package, uses PackageFactory.PackageType.
     */
    public void createPackage(String name, String notes, double price, double weight, LocalDateTime date,
                              String extraField, PackageFactory.PackageType type) {
        PackageBase newPackage = pkgFactory.getInstance(type, name, notes, price, weight, date, extraField);
        masterListOfPackages.add(newPackage);
    }

    /**
     * Helper method for adjusting a given Package.
     * Will either remove package or change its delivery state.
     * @param pkg The package to be adjusted.
     * @param option Which adjustment method is selected, based on constants from this class.
     * @param newDeliveryStatus The new delivery status of package, false if option is to remove.
     */
    public void adjustPackage(PackageBase pkg, int option, boolean newDeliveryStatus) {
        if (option == REMOVE) {
            masterListOfPackages.remove(pkg);
        } else if (option == DELIVERY_STATUS) {
            pkg.setDeliveryStatus(newDeliveryStatus);
        }
    }

    /**
     * Method tells server to load or save list data.
     * @param dataMode Determines whether data will be saved or loaded.
     */
    public void arrayData(int dataMode) {
        if(dataMode == DATA_SAVE){
            //tell server to save list
        } else if (dataMode == DATA_LOAD){
            //tell server to load list
        }
    }
}
