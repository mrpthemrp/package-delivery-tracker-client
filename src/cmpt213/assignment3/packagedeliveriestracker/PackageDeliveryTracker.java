package cmpt213.assignment3.packagedeliveriestracker;

import cmpt213.assignment3.packagedeliveriestracker.gson.extras.RuntimeTypeAdapterFactory;
import cmpt213.assignment3.packagedeliveriestracker.model.Book;
import cmpt213.assignment3.packagedeliveriestracker.model.Electronic;
import cmpt213.assignment3.packagedeliveriestracker.model.PackageBase;
import cmpt213.assignment3.packagedeliveriestracker.model.Perishable;
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

public class PackageDeliveryTracker {

    private final static int DATA_SAVE = 1;
    private final static int DATA_LOAD = 2;
    public static Gson gson;
    private static File gsonFile;
    private static ArrayList<PackageBase> listOfPackages;

    private PackageDeliveryTracker() {

        listOfPackages = new ArrayList<>();

        //code taken from email from TA Divye
        String fs = File.separator;
        String[] pathNames = {"src", "cmpt213", "assignment3", "packagedeliveriestracker", "gson"};
        String path = String.join(fs, pathNames);
        gsonFile = new File(path + fs + "list.json");

        setGsonBuilder();
        arrayData(DATA_LOAD);
    }

    public static void main(String[] args) {

        PackageDeliveryTracker pkgTrkr = new PackageDeliveryTracker();
        TextMenu menu = new TextMenu("Package Options Menu");

        int userInput;
        boolean endProgram = false;
        while (!endProgram) {
            //Update list order to reflect changes
            menu.updateList(listOfPackages);

            //Print Menu to screen and get input
            System.out.println();
            menu.displayMenu();
            userInput = menu.inputIntegerTryCatch(1, 7, "Choose a menu option by entering" +
                    " a whole number between 1 and 7.", "Invalid input. Enter a number between 1 and 7", "Your input: ");
            menu.printMenuOption(userInput - 1); //-1 to account for indexing in array

            //Handle correct input
            switch (userInput) {
                case 1, 4, 5 -> menu.listPackages(userInput, listOfPackages);
                case 2 -> listOfPackages.add(menu.createPackage());
                case 3 -> menu.changeAPackage(listOfPackages,
                        "Which package would you like to remove?",
                        "Remove package # ", 1);
                case 6 -> menu.changeAPackage(listOfPackages,
                        "Which package has been delivered?",
                        "Delivered package # ", 6);
                case 7 -> {
                    System.out.println("Saving data ...");
                    pkgTrkr.arrayData(DATA_SAVE);
                    System.out.println("Data saved!\n");
                    System.out.println("Thank you for using this program!\nProgram will now exit.");
                    endProgram = true;
                }
            }

        }
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
                for (PackageBase p : listOfPackages) {
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

            listOfPackages = newArray;
        }
    }
}
