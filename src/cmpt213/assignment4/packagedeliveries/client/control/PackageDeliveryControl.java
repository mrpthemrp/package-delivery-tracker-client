package cmpt213.assignment4.packagedeliveries.client.control;

import cmpt213.assignment4.packagedeliveries.client.view.PackageDeliveryGUI;
import cmpt213.assignment4.packagedeliveries.client.view.util.Util;
import cmpt213.assignment4.packagedeliveries.client.model.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class creates a PackageDeliveryControl object which manages data from the server.
 * Data is passed to the UI from this class.
 *
 * @author Deborah Wang
 */
public class PackageDeliveryControl {
    private static final int NONE = -1;
    public final static int ADD = 0;
    public final static int REMOVE = 1;
    public final static int DELIVERY_STATUS = 2;
    public final static String GET_ALL = "listAll";
    public final static String GET_OVERDUE = "listOverduePackage";
    public final static String GET_UPCOMING = "listUpcomingPackage";
    public final static String POST_ADD_PACKAGE = "addPackage";
    public final static String POST_REMOVE_PACKAGE = "removePackage";
    public final static String POST_MARK_DELIVERED = "markPackageAsDelivered";
    public final static String EXIT = "exit";
    private final ActionListener parentListener;
    public static ArrayList<PackageBase> masterListOfPackages;
    private static ArrayList<PackageBase> upcomingPackages;
    private static ArrayList<PackageBase> overduePackages;
    private final PackageFactory pkgFactory;
    private final ServerConnection server;
    private Gson gson;

    /**
     * Constructs a PackageDeliveryControl Object.
     * Initializes class fields and also loads in any data from the JSON list.
     *
     * @param al The action listener of the parent class, used to help refresh screen.
     */
    public PackageDeliveryControl(ActionListener al) {
        this.parentListener = al;
        this.pkgFactory = new PackageFactory();
        setGsonBuilder();

        this.server = new ServerConnection();

        masterListOfPackages = new ArrayList<>();
        upcomingPackages = new ArrayList<>();
        overduePackages = new ArrayList<>();

        updateAllLists();
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
        ArrayList<PackageBase> currentList = getAListOfPackages(PackageDeliveryGUI.currentState);
        int packageIndex = currentList.indexOf(pkg);
        if (option == REMOVE) {
            loadAList(server.postMessage(POST_REMOVE_PACKAGE, option, gson.toJson(pkg)
                    , packageIndex, newDeliveryStatus), currentList);
        } else if (option == DELIVERY_STATUS) {
            loadAList(server.postMessage(POST_MARK_DELIVERED, option, gson.toJson(pkg)
                    , packageIndex, newDeliveryStatus), currentList);
        }
        updateAllLists();
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
        loadAList(server.postMessage(POST_ADD_PACKAGE, ADD, gson.toJson(newPackage, newPackage.getClass().getGenericSuperclass()), NONE, false),
                masterListOfPackages);
        updateAllLists();
    }

    /**
     * Helper method that allows the UI to access the lists.
     *
     * @param currentState Current state of UI tells method which list to return.
     * @return Returns an ArrayList based on the current state.
     */
    public ArrayList<PackageBase> getAListOfPackages(Util.SCREEN_STATE currentState) {
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
     * Helper method that is called whenever the GUI exits.
     * Sends all data to server for saving.
     */
    public void saveClientData() {
        updateAllLists();
        server.getMessage(EXIT);
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
     * Helper method that refreshes all list data from the server.
     * Then refreshes screen to reflect changes.
     */
    private void updateAllLists() {
        loadAList(server.getMessage(GET_ALL), masterListOfPackages);
        loadAList(server.getMessage(GET_UPCOMING), upcomingPackages);
        loadAList(server.getMessage(GET_OVERDUE), overduePackages);
        parentListener.actionPerformed(new ActionEvent(PackageDeliveryControl.class, ActionEvent.ACTION_PERFORMED, "UPDATE"));
    }

    /**
     * Helper method that deserializes a JSON String from the server.
     *
     * @param stringArray The JSON String from the server.
     * @param list        The list to append the JSON string items to.
     */
    private void loadAList(String stringArray, ArrayList<PackageBase> list) {
        list.clear();
        JsonArray tempArray = gson.fromJson(stringArray, JsonArray.class);
        if (tempArray != null) {
            for (int i = 0; i < tempArray.size(); i++) {
                list.add(gson.fromJson(tempArray.get(i), PackageBase.class));
            }
        }
    }

}
