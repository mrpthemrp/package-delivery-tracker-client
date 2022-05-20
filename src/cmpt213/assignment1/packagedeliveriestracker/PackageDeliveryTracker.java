package cmpt213.assignment1.packagedeliveriestracker;

import java.util.ArrayList;
import java.util.Scanner;

public class PackageDeliveryTracker {
    private TextMenu menu;
    private ArrayList<Package> listOfPackages;

    public PackageDeliveryTracker () {
        menu = new TextMenu("Package Menu");
        listOfPackages = new ArrayList<>();
    }

    public static void main(String[] args) {
        PackageDeliveryTracker self = new PackageDeliveryTracker();

        self.menu.getInput();

        //System.out.println("Main works");
        //use a switch case for the menu!
    }
}
