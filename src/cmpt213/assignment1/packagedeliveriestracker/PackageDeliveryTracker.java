package cmpt213.assignment1.packagedeliveriestracker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
//GSON from victor: https://www.youtube.com/watch?v=kooiBuJlcFg
public class PackageDeliveryTracker implements Comparator<Package> {

    private static Gson gson;
    private static File gsonFile;

    @Override
    public int compare(Package o1, Package o2) {
        if (o1.getExpectedDeliveryDate().isBefore(o2.getExpectedDeliveryDate())) {
            return -1;
        }
        return 0;
    }

    public PackageDeliveryTracker() {
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

        gsonFile = new File("src\\cmpt213\\assignment1\\packagedeliveriestracker\\gsondata\\list.json");
    }

    public static void main(String[] args) throws IOException {
        PackageDeliveryTracker pkgTrkr = new PackageDeliveryTracker();
        TextMenu menu = new TextMenu("Package Menu");
        ArrayList<Package> listOfPackages = pkgTrkr.loadData();

        int userInput;
        boolean endProgram = false;
        while (!endProgram) {
            listOfPackages.sort(pkgTrkr);
            System.out.println();
            menu.displayMenu();
            userInput = menu.getMenuInput();
            menu.printMenuOption(userInput - 1);

            switch (userInput) {
                case 1, 4, 5 -> menu.listPackages(userInput, listOfPackages);
                case 2 -> listOfPackages.add(menu.createPackage());
                case 3 ->
                        menu.changeAPackage(listOfPackages, "Which package would you like to remove?", "Remove package # ", 1);
                case 6 ->
                        menu.changeAPackage(listOfPackages, "Which package has been delivered?", "Delivered package # ", 5);
                case 7 -> {
                    pkgTrkr.saveData(listOfPackages);
                    System.out.println("Program will now exit.");
                    endProgram = true;
                }
            }

        }
    }

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
                throw new RuntimeException(e);
            }
        }

        return newArray;

    }

    public void saveData(ArrayList<Package> listOfPackages) throws IOException {
        if (!gsonFile.exists()) {
            gsonFile.createNewFile();
        }

        try {
            FileWriter fileWrite = new FileWriter(gsonFile);
            gson.toJson(listOfPackages, fileWrite);
            fileWrite.close();
        } catch (IOException e) {
            throw new IOException();
        }

    }

}
