package cmpt213.assignment4.packagedeliveries.client.control;

import cmpt213.assignment4.packagedeliveries.client.gson.extras.RuntimeTypeAdapterFactory;
import cmpt213.assignment4.packagedeliveries.client.model.Package;
import cmpt213.assignment4.packagedeliveries.client.view.util.Util;
import cmpt213.assignment4.packagedeliveries.client.model.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class creates a PackageDeliveryControl object which manages data for the program.
 *
 * @author Deborah Wang
 */
public class PackageDeliveryControl {
    public final static int DATA_SAVE = 1;
    private final static int DATA_LOAD = 2;
    public final static int ADD = 0;
    public final static int REMOVE = 1;
    public final static int DELIVERY_STATUS = 2;
    private static ArrayList<PackageBase> masterListOfPackages;
    private static ArrayList<PackageBase> upcomingPackages;
    private static ArrayList<PackageBase> overduePackages;
    private final PackageFactory pkgFactory;
    private final ServerConnection server;
    private Gson gson;

    /**
     * Constructs a PackageDeliveryControl Object.
     * Initializes class fields and also loads in any data from the JSON list.
     */
    public PackageDeliveryControl() {
        this.pkgFactory = new PackageFactory();
        setGsonBuilder();

        this.server = new ServerConnection();

        masterListOfPackages = new ArrayList<>();
        upcomingPackages = new ArrayList<>();
        overduePackages = new ArrayList<>();

        arrayData(DATA_LOAD);
    }

    /**
     * Method that creates a new Package using the PackageFactory class.
     * Update lists after package creation.
     *
     * @param name       Name of the package.
     * @param notes      Any notes for the package.
     * @param price      Price of the package.
     * @param weight     Weight of the package.
     * @param date       Expected Delivery Date of the package.
     * @param extraField Extra field of the package, in String form already.
     * @param type       Subclass type of the package, uses PackageFactory.PackageType.
     */
    public void createPackage(String name, String notes, double price, double weight, LocalDateTime date,
                              String extraField, PackageFactory.PackageType type) {
        PackageBase newPackage = pkgFactory.getInstance(type, name, notes, price, weight, date, extraField);
        masterListOfPackages.add(newPackage);
    }

    /**
     * Helper method for adjusting a given Package.
     * Will either remove package or change its delivery state.
     *
     * @param pkg               The package to be adjusted.
     * @param option            Which adjustment method is selected, based on constants from this class.
     * @param newDeliveryStatus The new delivery status of package, false if option is to remove.
     */
    public void adjustPackage(PackageBase pkg, int option, boolean newDeliveryStatus) {
        if (option == REMOVE) {
            server.postMessage(ServerConnection.POST_REMOVE_PACKAGE, REMOVE,gson.toJson(pkg));
            masterListOfPackages.remove(pkg); // to delete
        } else if (option == DELIVERY_STATUS) {
            pkg.setDeliveryStatus(newDeliveryStatus);
            server.postMessage(ServerConnection.POST_MARK_DELIVERED, DELIVERY_STATUS,gson.toJson(pkg));
        }
        //to update list
    }

    /**
     * Helper method that allows the UI to access the lists.
     *
     * @param currentState Current state of UI tells method which list to return.
     * @return Returns an ArrayList based on the current state.
     */
    public ArrayList<PackageBase> getAListOfPackages(Util.SCREEN_STATE currentState) {
        updateAllLists(ServerConnection.POST);
        updateAllLists(ServerConnection.GET);
        switch (currentState) {
            case LIST_ALL -> {
                return masterListOfPackages;
            }
            case UPCOMING -> {
                return upcomingPackages;
            }
            case OVERDUE -> {
                return overduePackages;
            }
        }
        return null;
    }

    /**
     * Helper method that sets up the GSON logic for this application.
     * Registers subclasses of PackageBase into the RuntimeTypeAdapter.
     */
    private void setGsonBuilder() {
        RuntimeTypeAdapterFactory<PackageBase> packageAdapterFactory = RuntimeTypeAdapterFactory.of(PackageBase.class, "type")
                .registerSubtype(Book.class, "Book")
                .registerSubtype(Perishable.class, "Perishable")
                .registerSubtype(Electronic.class, "Electronic");

        gson = new GsonBuilder()
                .registerTypeAdapter(DateTimeFormatter.class, new TypeAdapter<DateTimeFormatter>() {
                    @Override
                    public void write(JsonWriter jsonWriter, DateTimeFormatter dateTimeFormatter) throws IOException {
                        jsonWriter.value(dateTimeFormatter.toString());
                    }

                    @Override
                    public DateTimeFormatter read(JsonReader jsonReader) throws IOException {
                        return DateTimeFormatter.ofPattern(jsonReader.nextString());
                    }
                })
                .registerTypeAdapter(LocalDateTime.class, new TypeAdapter<LocalDateTime>() {
                    @Override
                    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
                        jsonWriter.value(localDateTime.toString());
                    }

                    @Override
                    public LocalDateTime read(JsonReader jsonReader) throws IOException {
                        return LocalDateTime.parse(jsonReader.nextString());
                    }
                })
                .registerTypeAdapterFactory(packageAdapterFactory)
                .create();
    }

    /**
     * Method tells server to load or save list data.
     *
     * @param dataMode Determines whether data will be saved or loaded.
     */
    public void arrayData(int dataMode) {
        if (dataMode == DATA_SAVE) {
            updateAllLists(ServerConnection.POST);
        } else if (dataMode == DATA_LOAD) {
            updateAllLists(ServerConnection.GET);
        }//end of else
    }

    private void updateAllLists(String connection) {
        loadAList(ServerConnection.GET_ALL, masterListOfPackages, connection);
        loadAList(ServerConnection.GET_UPCOMING, upcomingPackages,connection);
        loadAList(ServerConnection.GET_OVERDUE, overduePackages,connection);
    }

    private void loadAList(String command, ArrayList<PackageBase> list, String requestType) {
        if(requestType.equals(ServerConnection.GET)){
            list.clear();
            String stringArray = this.server.getMessage(command);
            JsonArray tempArray = gson.fromJson(stringArray, JsonArray.class);
            if (tempArray != null) {
                for (int i = 0; i < tempArray.size(); i++) {
                    list.add(gson.fromJson(tempArray.get(i), PackageBase.class));
                }
            }
        } else if (requestType.equals(ServerConnection.POST)){
            //do something
        }
    }
}
