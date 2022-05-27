package cmpt213.assignment1.packagedeliveriestracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TextMenu {
    private static final int LIST_PACKAGES = 1;
    private static final int LIST_OVERDUE_PACKAGES = 4;
    private static final int LIST_UPCOMING_PACKAGES = 5;
    private static final String NO_PACKAGE_MESSAGE = "No packages to show";
    private final String menuTitle;
    private boolean control;
    private final ArrayList<String> menuOptions;
    private final LocalDateTime currentTime;
    private final DateTimeFormatter monthDateYear;
    private final Scanner input = new Scanner(System.in);

    public TextMenu(String menuTitle) {
        this.menuTitle = menuTitle;
        this.control = false;
        this.currentTime = LocalDateTime.now();
        monthDateYear = DateTimeFormatter.ofPattern("MMM dd, yyyy | hh:mm a");

        //initialize ArrayList
        menuOptions = new ArrayList<>();
        menuOptions.add("List All Packages");
        menuOptions.add("Add A Package");
        menuOptions.add("Remove A Package");
        menuOptions.add("List Overdue Packages");
        menuOptions.add("List Upcoming Packages");
        menuOptions.add("Mark Package As Delivered");
        menuOptions.add("Exit");
    }

    //QUICKSORT
    //reference from: https://www.geeksforgeeks.org/quick-sort/
    private void quickSortSwap(ArrayList<Package> packageList, int i, int j) {
        Package temp = packageList.get(i);
        packageList.set(i, packageList.get(j));
        packageList.set(j, temp);
    }

    private int quickSortPartition(ArrayList<Package> packageList, int low, int high) {
        //set pivot
        LocalDateTime pivot = packageList.get(high).getExpectedDeliveryDate();

        int i = (low - 1);

        for (int j = low; j <= (high - 1); j++) {
            if (packageList.get(j).getExpectedDeliveryDate().isBefore(pivot)) {
                i++;
                quickSortSwap(packageList, i, j);
            }
        }
        quickSortSwap(packageList, i + 1, high);
        return (i + 1);
    }

    //sorts by LocalDateTime field!
    private void quickSortPackageList(ArrayList<Package> unsortedList, int low, int high) {
        if (low < high) {
            int partitionIndex = quickSortPartition(unsortedList, low, high);

            quickSortPackageList(unsortedList, low, partitionIndex - 1);
            quickSortPackageList(unsortedList, partitionIndex + 1, high);
        }
    }

    //INPUT
    public int getMenuInput() throws NumberFormatException {
        boolean correctInput = false;
        int userInput = 0;

        while (!correctInput) {
            try {
                System.out.println("Choose a menu option by entering" +
                        " a whole number between 1 and 7.");
                System.out.print("Your input: ");
                userInput = Integer.parseInt(input.nextLine());

                if (userInput > 0 && userInput <= 7) {
                    correctInput = true;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Enter a number between 1 and 7");
            }
        }

        return userInput;
    }

    private double inputTryCatch(String question, String errorLine, boolean isDouble) {
        double finalNumber = 0.0;
        this.control = false;

        do {
            System.out.print(question);
            try {
                if (isDouble) {
                    finalNumber = Double.parseDouble(input.nextLine());
                } else {
                    finalNumber = Integer.parseInt(input.nextLine());
                }
                System.out.println();

                this.control = true;
            } catch (NumberFormatException nfe) {
                System.err.println(errorLine+"\n");
            }

        } while (!this.control);

        return finalNumber;
    }

    public Package createPackage() throws NumberFormatException {

        control = false;
        String name;
        do {
            System.out.print("Enter the name of your package: ");
            name = input.nextLine();

            if(name.isEmpty() || name.isBlank()){
                System.out.println("name cannot be empty, please enter a name");
            } else{
                System.out.println();
                this.control = true;
            }
        }while (!this.control);

        System.out.print("Enter any notes for your package: ");
        String notes = input.nextLine();
        System.out.println();

        double price = inputTryCatch("Enter the price for your package (in dollars): $",
                "Incorrect Input! Try again.", true);

        double weight = inputTryCatch("Enter the weight of your package (in kg): ",
                "Incorrect Input! Try again.", true);

        int year = (int) (inputTryCatch("Enter the year of the expected delivery date: ",
                "error", false));

        int month = (int) (inputTryCatch("Enter the month of the expected delivery date (1 - 12): ",
                "error", false));

        int day = (int) (inputTryCatch("Enter the day of the expected delivery date (1 - 28/29/30/31): ",
                "error", false));

        int hour = (int) (inputTryCatch("Enter the hour of the expected delivery date (0 - 23): ",
                "error", false));

        int minute = (int) (inputTryCatch("Enter the minute of the expected delivery date (0 - 59): ",
                "error", false));

        LocalDateTime deliveryDate = LocalDateTime.of(year, month, day, hour, minute);

        return new Package(name, notes, price, weight, deliveryDate);
    }

    //PRINT
    public void displayMenu() {
        String hashTags = "####";// to account for spaces
        for (int i = 0; i < menuTitle.length(); i++) {
            hashTags = hashTags.concat("#");
        }
        System.out.println(hashTags + "\n# " + menuTitle + " #\n" + hashTags);
        System.out.println("Today is: " + currentTime.format(monthDateYear));
        for (int j = 0; j < menuOptions.size(); j++) {
            System.out.println((j + 1) + ": " + menuOptions.get(j));
        }
    }

    public void printMenuOption(int option) {
        System.out.println(menuOptions.get(option));
    }

    private void printSinglePackage(int index, ArrayList<Package> listOfPackages) {
        System.out.println("Package #" + (index + 1) + "\n" +
                listOfPackages.get(index).toString() + "\n");
    }

    public void listPackages(int menuOption, ArrayList<Package> listOfPackages) {
        if (listOfPackages.size() == 0) {
            System.out.println(NO_PACKAGE_MESSAGE);
        } else {
            for (int i = 0; i < listOfPackages.size(); i++) {

                if (menuOption == LIST_PACKAGES) {
                    printSinglePackage(i, listOfPackages);

                } else {
                    ArrayList<Package> sortedList = new ArrayList<>(listOfPackages);
                    quickSortPackageList(sortedList, 0, sortedList.size() - 1);
                    int packageCount = 0;
                    if (menuOption == LIST_OVERDUE_PACKAGES) {
                        if (isOverdue(sortedList.get(i).getExpectedDeliveryDate())) {
                            packageCount++;
                            printSinglePackage(i, sortedList);
                        }

                    } else if (menuOption == LIST_UPCOMING_PACKAGES) {
                        if (!isOverdue(sortedList.get(i).getExpectedDeliveryDate())) {
                            packageCount++;
                            printSinglePackage(i, sortedList);
                        }
                    }

                    if (packageCount == 0) {
                        System.out.println(NO_PACKAGE_MESSAGE);
                    }

                }
            }
        }

    }

    //OTHER -to be organized
    private boolean isOverdue(LocalDateTime packageDate) {
        return packageDate.isBefore(currentTime);
    }

    public void removeAPackage (ArrayList<Package> packageList) throws NumberFormatException{
        if(packageList.size() ==0){
            System.out.println(NO_PACKAGE_MESSAGE);
            return;
        }

        this.control = false;
        do{
            try{
                System.out.println("\nList of packages:");
                listPackages(1, packageList);
                System.out.println();

                System.out.println("Which package would you like to remove?\n" +
                        "Enter 0 to cancel removing a package.");
                System.out.print("Remove package # ");
                int packageNumber = Integer.parseInt(input.nextLine());

                if (packageNumber == 0){
                    System.out.println("Cancel selected.\nReturning to Main Menu.");
                    control = true;
                }

                packageNumber--; //to get package index
                if (packageNumber >= 0 && packageNumber < packageList.size()) {
                    System.out.println("Removed Package #"+(packageNumber+1));
                    packageList.remove(packageNumber);
                    this.control = true;
                } else {
                    throw new NumberFormatException();
                }
            } catch(NumberFormatException nfe){
                System.err.println("Package does not exist, try again.");
            }
        } while (!control);
    }

} // TextMenu.java
