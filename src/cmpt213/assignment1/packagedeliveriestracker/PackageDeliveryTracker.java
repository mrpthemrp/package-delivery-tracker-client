package cmpt213.assignment1.packagedeliveriestracker;

import java.util.ArrayList;

public class PackageDeliveryTracker {
    private TextMenu menu;
    private ArrayList<Package> listOfPackages;

    public PackageDeliveryTracker () {
        menu = new TextMenu("Package Menu");
        listOfPackages = new ArrayList<>();
    }

    public static void main(String[] args) {
        PackageDeliveryTracker tracker = new PackageDeliveryTracker();
        TextMenu menu = tracker.menu;

        int userInput = 0;
        boolean endProgram = false;
        while(!endProgram){
            menu.displayMenu();
            userInput = menu.getInput();
            switch (userInput){
                case 1:
                    System.out.println("case 1");
                    break;
                case 2:
                    System.out.println("case 2");
                    break;
                case 3:
                    System.out.println("case 3");
                    break;
                case 4:
                    System.out.println("case 4");
                    break;
                case 5:
                    System.out.println("case 5");
                    break;
                case 6:
                    System.out.println("case 6");
                    break;
                case 7:
                    System.out.println("Program will now exit.");
                    endProgram = true;
                    break;
            }

        }
    }
}
