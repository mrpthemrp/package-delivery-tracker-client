package cmpt213.assignment1.packagedeliveriestracker;

import java.util.*;

public class PackageDeliveryTracker {

    public static void main(String[] args) {
        TextMenu menu = new TextMenu("Package Menu");
        ArrayList<Package> listOfPackages = new ArrayList<>();

        Package bob = menu.createPackage();

        bob.printPackageInfo();

//        int userInput;
//        boolean endProgram = false;
//        while(!endProgram){
//            menu.displayMenu();
//            userInput = menu.getInput();
//            switch (userInput) {
//                case 1 -> System.out.println("case 1");
//                case 2 -> System.out.println("case 2");
//                case 3 -> System.out.println("case 3");
//                case 4 -> System.out.println("case 4");
//                case 5 -> System.out.println("case 5");
//                case 6 -> System.out.println("case 6");
//                case 7 -> {
//                    System.out.println("Program will now exit.");
//                    endProgram = true;
//                }
//            }
//
//        }
    }
}
