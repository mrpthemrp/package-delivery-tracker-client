package cmpt213.assignment2.packagedeliveriestracker;
import cmpt213.assignment2.packagedeliveriestracker.model.*;

import cmpt213.assignment2.packagedeliveriestracker.textui.TextMenu;
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
public class PackageDeliveryTracker {

    private final static int DATA_SAVE = 1;
    private final static int DATA_LOAD = 2;

    private static Gson gson;
    private static File gsonFile;
    private static ArrayList<PackageBase> listOfPackages;

    /**
     * Constructor for PackageDeliveryTracker Class; initializes GSON object,
     * File object for data saving, and an ArrayList object.
     */
    private PackageDeliveryTracker() {

        //code taken from email from TA Divye
        String fs = File.separator;
        String[] pathNames = { "src", "cmpt213", "assignment2", "packagedeliveriestracker", "model" };
        String path = String.join(fs, pathNames);
        gsonFile = new File(path + fs + "list.json");

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

        listOfPackages = new ArrayList<>();
        arrayData(DATA_LOAD);
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

            //Print Menu to screen and get input
            System.out.println();
            menu.displayMenu();
            userInput = menu.inputIntegerTryCatch(1,7 ,"Choose a menu option by entering" +
                    " a whole number between 1 and 7." ,"Invalid input. Enter a number between 1 and 7" );
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

    private void arrayData(int bob) {
        ArrayList<PackageBase> newArray = new ArrayList<>();
        
        if(bob == DATA_SAVE){
            try {
                FileWriter fileWrite = new FileWriter(gsonFile);
                gson.toJson(listOfPackages, fileWrite);
                fileWrite.close();
            } catch (IOException e) {
                System.out.println("Could not save data!");
            }
            
        } else if (bob == DATA_LOAD) {
            if (gsonFile.exists()) {
                try {
                    FileReader fileRead = new FileReader(gsonFile);
                    Type type = new TypeToken<ArrayList<PackageBase>>() {
                    }.getType();

                    newArray = gson.fromJson(fileRead, type);
                    fileRead.close();
                } catch (IOException e) {
                    System.out.println("Could not load data!");
                }
            }

            listOfPackages = newArray;
        }


    }
}
