package cmpt213.assignment2.packagedeliveriestracker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * PackageDeliveryTracker class models a delivery tracker and
 * input can be read in and saved as data to a JSON file.
 * <p>
 * Implements custom Comparator to sort Package objects
 * from oldest to newest delivery date (LocalDateTime).
 * <p>
 * GSON Reference (Dr. Victor Cheung): <a href="https://www.youtube.com/watch?v=kooiBuJlcFg"></a>
 *
 * @author Deborah Wang
 */
public class PackageDeliveryTracker implements Comparator<Package> {

    private static Gson gson;
    private static File gsonFile;
    private static ArrayList<Package> listOfPackages;

    /**
     * Constructor for PackageDeliveryTracker Class; initializes GSON object,
     * File object for data saving, and an ArrayList object.
     */
    public PackageDeliveryTracker() {

        gsonFile = new File("src\\cmpt213\\assignment2\\packagedeliveriestracker\\model\\list.json");
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new TypeAdapter<LocalDateTime>() {
                    @Override
                    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
                        jsonWriter.value(localDateTime.toString());
                    }

                    @Override
                    public LocalDateTime read(JsonReader jsonReader) throws IOException {
                        return LocalDateTime.parse(jsonReader.nextString());
                    }
                }).create();

        listOfPackages = loadData();
    }

    /**
     * Main method loads any previous data, asks for user input,
     * sorts the data list after each case, and saves data after system exits.
     *
     * @param args Console input from user is taken as a String.
     */
    public static void main(String[] args) {

        PackageDeliveryTracker pkgTrkr = new PackageDeliveryTracker();
        TextMenu menu = new TextMenu("Package Options Menu");

        int userInput;
        boolean endProgram = false;
        while (!endProgram) {
            //Update list order to reflect changes
            listOfPackages.sort(pkgTrkr);

            //Print Menu to screen and get input
            System.out.println();
            menu.displayMenu();
            userInput = menu.getMenuInput();
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
                    pkgTrkr.saveData();
                    System.out.println("Data saved!\n");
                    System.out.println("Thank you for using this program!\nProgram will now exit.");
                    endProgram = true;
                }
            }

        }
    }

    /**
     * Override compare method to implement Comparator;
     * compares if a package's delivery date is older or newer.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return Returns negative is o1 is older than o2, returns 0 if o1 is newer.
     */
    @Override
    public int compare(Package o1, Package o2) {
        if (o1.getExpectedDeliveryDate().isBefore(o2.getExpectedDeliveryDate())) {
            return -1;
        }
        return 0;
    }

    /**
     * LoadData method checks for any previously saved
     * data and loads it into ArrayList, otherwise initializes new ArrayList.
     *
     * @return Returns an ArrayList object.
     */
    public ArrayList<Package> loadData() {
        ArrayList<Package> newArray = new ArrayList<>();

        if (gsonFile.exists()) {
            try {
                FileReader fileRead = new FileReader(gsonFile);
                Type type = new TypeToken<ArrayList<Package>>() {
                }.getType();

                newArray = gson.fromJson(fileRead, type);
                fileRead.close();
            } catch (IOException e) {
                System.out.println("Could not load data!");
            }
        }

        return newArray;
    }

    /**
     * Saves data from parameter to JSON format and then to JSON file;
     * creates new File if there is no existing JSON file.
     */
    public void saveData() {

        try {
            FileWriter fileWrite = new FileWriter(gsonFile);
            gson.toJson(listOfPackages, fileWrite);
            fileWrite.close();
        } catch (IOException e) {
            System.out.println("Could not save data!");
        }

    }

}
