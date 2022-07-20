package cmpt213.assignment3.packagedeliveries.control;

import cmpt213.assignment3.packagedeliveries.gson.extras.RuntimeTypeAdapterFactory;
import cmpt213.assignment3.packagedeliveries.model.*;
import cmpt213.assignment3.packagedeliveries.view.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class PackageDeliveryControl {

    public final static int REMOVE = 1;
    public final static int DELIVERY_STATUS = 2;

    private final PackageFactory pkgFactory;
    private LocalDateTime currentTime;
    private final static int DATA_SAVE = 1;
    private final static int DATA_LOAD = 2;
    public static Gson gson;
    private static File gsonFile;
    private static ArrayList<PackageBase> masterListOfPackages;
    private static ArrayList<PackageBase> upcomingPackages;
    private static ArrayList<PackageBase> overduePackages;

    public PackageDeliveryControl() {
        //textmenu

        this.pkgFactory = new PackageFactory();
        this.currentTime = LocalDateTime.now();


        //tracker
        masterListOfPackages = new ArrayList<>();
        upcomingPackages = new ArrayList<>();
        overduePackages = new ArrayList<>();

        //code taken from email from TA Divye
        String fs = File.separator;
        String[] pathNames = {"src", "cmpt213", "assignment3", "packagedeliveries", "gson"};
        String path = String.join(fs, pathNames);
        gsonFile = new File(path + fs + "list.json");

        setGsonBuilder();
        arrayData(DATA_LOAD);
    }

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

    private void arrayData(int dataMode) {
        ArrayList<PackageBase> newArray = new ArrayList<>();

        if (dataMode == DATA_SAVE) {
            try {
                FileWriter fileWrite = new FileWriter(gsonFile);
                JsonArray toJsonArray = new JsonArray();
                //convert each object to Json string
                for (PackageBase p : masterListOfPackages) {
                    if (p != null) {
                        toJsonArray.add(gson.toJsonTree(p, PackageBase.class));
                    }
                }
                gson.toJson(toJsonArray, fileWrite);
                fileWrite.close();
            } catch (IOException e) {
                System.out.println("Could not save data!");
            }

        } else if (dataMode == DATA_LOAD) {
            if (gsonFile.exists()) {
                try {
                    FileReader fileRead = new FileReader(gsonFile);
                    JsonArray jsonArray = gson.fromJson(fileRead, JsonArray.class);
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.size(); i++) {
                            newArray.add(gson.fromJson(jsonArray.get(i), PackageBase.class));
                        }
                    }
                    fileRead.close();
                } catch (IOException e) {
                    System.out.println("Could not load data!");
                }
            }

            masterListOfPackages = newArray;
        }
    }

    //text menu methods

    public ArrayList<PackageBase> listPackages(Util.SCREEN_STATE currentState) {
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

    private void updateCurrentTime() {
        this.currentTime = LocalDateTime.now();
    }


    public PackageBase createPackage(String name, String notes, double price, double weight, LocalDateTime date,
                                     String extraField, PackageFactory.PackageType type) {

        //Set extra field
        PackageBase newPackage;
        switch (type) {
            case BOOK -> {
                newPackage = pkgFactory.getInstance(type, name, notes, price, weight, date, extraField);
                System.out.println("Book: " + name + " has been added to the list.");//change this to a dialog message
                return newPackage;
            }
            case PERISHABLE -> {
                newPackage = pkgFactory.getInstance(type, name, notes, price, weight, date, extraField);
                System.out.println("Perishable: " + name + " has been added to the list.");//change this to a dialog message
                return newPackage;
            }
            case ELECTRONIC -> {
                newPackage = pkgFactory.getInstance(type, name, notes, price, weight, date, extraField);
                System.out.println("Electronic: " + name + " has been added to the list."); //change this to a dialog message
                return newPackage;

            }
        }
        return null;
    }

    public void adjustPackage(PackageBase pkg, int index, int option, boolean newDeliveryStatus) {
        if (option == REMOVE) {
            masterListOfPackages.remove(index);
            if(upcomingPackages.contains(pkg)){
                upcomingPackages.remove(pkg);
            }
            if(overduePackages.contains(pkg)){
                overduePackages.remove(pkg);
            }
        } else if (option == DELIVERY_STATUS) {
            pkg.setDeliveryStatus(newDeliveryStatus);
        }
        updateLists();
    }

    private boolean isOverdue(LocalDateTime packageDate) {
        return packageDate.isBefore(currentTime);
    }

    public final void updateLists() {
        //add necessary packages to lists
        for (PackageBase tempPkg : masterListOfPackages) {
            if (!tempPkg.isDelivered()) {
                if (isOverdue(tempPkg.getExpectedDeliveryDate())) {
                    if (!overduePackages.contains(tempPkg)) {
                        overduePackages.add(tempPkg);
                    }
                } else {
                    if (!upcomingPackages.contains(tempPkg)) {
                        upcomingPackages.add(tempPkg);
                    }
                }

            } else {
                if (upcomingPackages.contains(tempPkg)) {
                    upcomingPackages.remove(tempPkg);
                }
                if (overduePackages.contains(tempPkg)) {
                    overduePackages.remove(tempPkg);
                }
            }
        }

        //sort
        Collections.sort(masterListOfPackages);
        Collections.sort(upcomingPackages);
        Collections.sort(overduePackages);
    }

    public PackageBase getPackage(int i) {
        return masterListOfPackages.get(i);
    }

    public ArrayList<PackageBase> getAListOfPackages (Util.SCREEN_STATE currentState){
        switch (currentState){
            case LIST_ALL -> {return masterListOfPackages;}
            case UPCOMING -> {return upcomingPackages;}
            case OVERDUE -> {return overduePackages;}
        }
        return null;
    }
}
