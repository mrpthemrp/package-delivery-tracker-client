package cmpt213.assignment1.packagedeliveriestracker;

import java.util.*;

public class PackageDeliveryTracker {

    public static void main(String[] args) {
        TextMenu menu = new TextMenu("Package Menu");
        ArrayList<Package> listOfPackages = new ArrayList<>();

        int userInput;
        boolean endProgram = false;
        while (!endProgram) {
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
                    System.out.println("Program will now exit.");
                    endProgram = true;
                }
            }

        }
    }
}
