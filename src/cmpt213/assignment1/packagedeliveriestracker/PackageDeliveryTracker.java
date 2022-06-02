package cmpt213.assignment1.packagedeliveriestracker;

import com.google.gson.*;

import java.io.*;
import java.util.*;

public class PackageDeliveryTracker implements Comparator<Package>{

    private static Gson gsonSaver;
    private static File gsonFile;
    private final String filePath ="src\\cmpt213\\assignment1\\packagedeliveriestracker\\gsondata\\list.json";

    @Override
    public int compare(Package o1, Package o2) {
        if (o1.getExpectedDeliveryDate().isBefore(o2.getExpectedDeliveryDate())) {
            return -1;
        }
        return 0;
    }

    public PackageDeliveryTracker(){
        gsonSaver = new Gson();
        gsonFile = new File(filePath);
    }
    public static void main(String[] args) throws IOException {
        PackageDeliveryTracker pkgTrkr = new PackageDeliveryTracker();
        TextMenu menu = new TextMenu("Package Menu");
        ArrayList<Package> listOfPackages = new ArrayList<>();
//
//        if(gsonFile.exists()){
//            listOfPackages = gsonSaver.fromJson(gsonFile.toString(), new TypeToken<ArrayList<Package>>() {}.getType());
//        }

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
                case 3 -> menu.changeAPackage(listOfPackages, "Which package would you like to remove?","Remove package # ",1);
                case 6 -> menu.changeAPackage(listOfPackages,"Which package has been delivered?","Delivered package # ",5);
                case 7 -> {
                    pkgTrkr.saveData(listOfPackages);
                    System.out.println("Program will now exit.");
                    endProgram = true;
                }
            }

        }
    }

    public void saveData(ArrayList<Package> listOfPackages) throws IOException {
        if(!gsonFile.exists()){
            gsonFile.createNewFile();
        }
    }

}
