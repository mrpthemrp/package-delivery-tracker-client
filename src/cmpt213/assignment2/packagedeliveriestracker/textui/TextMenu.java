package cmpt213.assignment2.packagedeliveriestracker.textui;

import cmpt213.assignment2.packagedeliveriestracker.model.Package;
import cmpt213.assignment2.packagedeliveriestracker.model.PackageFactory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TextMenu class manages menu options, handles input,
 * prints to screen, and creates Package object
 *
 * @author Deborah Wang
 */
public class TextMenu {
    public static final String TYPE_BOOK = "BOOK";
    public static final String TYPE_ELECTRONIC = "ELECTRONIC";
    public static final String TYPE_PERISHABLE = "PERISHABLE";
    private static final int LIST_PACKAGES = 1;
    private static final int LIST_OVERDUE_PACKAGES = 4;
    private static final int LIST_UPCOMING_PACKAGES = 5;
    private static final int LIST_UNDELIVERED_PACKAGES = 6;
    private static final String NO_PACKAGE_MESSAGE = "No packages to show";
    private static final int EMPTY_VALUE = -1;
    private final String menuTitle;
    private final ArrayList<String> menuOptions;
    private final DateTimeFormatter monthDateYear;
    private final Scanner input = new Scanner(System.in);
    private final PackageFactory pkgFactory;
    private LocalDateTime currentTime;
    private boolean control;

    /**
     * Constructor for TextMenu, initializes fields,
     * adds menu options to an arrayList.
     *
     * @param menuTitle A String that is the menu's name.
     */
    public TextMenu(String menuTitle) {
        this.menuTitle = menuTitle;
        this.control = false;
        this.currentTime = LocalDateTime.now();
        this.monthDateYear = DateTimeFormatter.ofPattern("MMM dd, yyyy | hh:mm a");
        this.pkgFactory = new PackageFactory();


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

    /**
     * Method prints menu to console with proper format.
     */
    public void displayMenu() {
        updateCurrentTime();
        //Header lines
        String hashTags = "####";// to account for spaces
        for (int i = 0; i < menuTitle.length(); i++) {
            hashTags = hashTags.concat("#");
        }
        System.out.println(hashTags + "\n# " + menuTitle + " #\n" + hashTags);

        //Menu Options and Time
        System.out.println("Today is: " + currentTime.format(monthDateYear));
        for (int j = 0; j < menuOptions.size(); j++) {
            System.out.println((j + 1) + ": " + menuOptions.get(j));
        }
    }

    /**
     * Method gets and prints the menu option from user.
     *
     * @param option An Integer that holds a menu option,
     *               is already an index number for array access.
     */
    public void printMenuOption(int option) {
        System.out.println("\n" + menuOptions.get(option) + "\n");
    }

    public void listPackages(int menuOption, ArrayList<Package> listOfPackages) {
        //Checks for empty list
        if (listOfPackages.size() == 0) {
            System.out.println(NO_PACKAGE_MESSAGE);
        } else {
            int packageCount = 0;

            for (int i = 0; i < listOfPackages.size(); i++) {

                Package tempPkg = listOfPackages.get(i);

                if (menuOption == LIST_PACKAGES) {
                    packageCount++;
                    printSinglePackage(i, listOfPackages);
                } else if (menuOption == LIST_OVERDUE_PACKAGES && !tempPkg.getDeliveryStatus()) {
                    if (isOverdue(tempPkg.getExpectedDeliveryDate())) {
                        packageCount++;
                        printSinglePackage(i, listOfPackages);
                    }

                } else if (menuOption == LIST_UPCOMING_PACKAGES) {
                    if (!isOverdue(tempPkg.getExpectedDeliveryDate()) && !tempPkg.getDeliveryStatus()) {
                        packageCount++;
                        printSinglePackage(i, listOfPackages);
                    }
                } else if (menuOption == LIST_UNDELIVERED_PACKAGES) {
                    if (!tempPkg.getDeliveryStatus()) {
                        packageCount++;
                        printSinglePackage(i, listOfPackages);
                    }
                }
            }
            if (packageCount == 0) {
                System.out.println(NO_PACKAGE_MESSAGE);
            }
        }
    }

    public void changeAPackage(ArrayList<Package> packageList, String question,
                               String prompt, int menuOption) throws NumberFormatException {
        if (packageList.size() == 0) {
            System.out.println(NO_PACKAGE_MESSAGE);
        } else {
            this.control = false;
            while (!this.control) {

                System.out.println("\nList of packages:");
                listPackages(menuOption, packageList);
                System.out.println();

                System.out.println(question + "\nEnter 0 to cancel.");
                System.out.print(prompt);
                try {
                    int packageNumber = Integer.parseInt(input.nextLine());

                    if (packageNumber == 0) {
                        System.out.println("Cancel selected.\nReturning to Main Menu.");
                        this.control = true;
                    } else {

                        packageNumber--; //to get package index

                        if (menuOption == LIST_PACKAGES) {
                            if (packageNumber >= 0 && packageNumber < packageList.size()) {
                                System.out.println(packageList.get(packageNumber).getName() + " has been removed from the list.");
                                packageList.remove(packageNumber);
                                this.control = true;
                            } else {
                                throw new NumberFormatException();
                            }
                        } else if (menuOption == LIST_UNDELIVERED_PACKAGES) {
                            packageList.get(packageNumber).setDeliveryStatus(true);
                            System.out.println(packageList.get(packageNumber).getName() + " has been delivered.");
                            this.control = true;
                        }
                    }

                } catch (NumberFormatException nfe) {
                    System.out.println("\nPackage does not exist, try again.\n");
                }
            }
        }
    }

    private void updateCurrentTime() {
        this.currentTime = LocalDateTime.now();
    }

    private void printSinglePackage(int index, ArrayList<Package> listOfPackages) {

        long daysLeft = currentTime.until(
                listOfPackages.get(index).getExpectedDeliveryDate(),
                ChronoUnit.DAYS);

        String daysLeftPrompt = "\nTime left until delivery date: " + daysLeft + " days\n";


        //If delivered, then there is no prompt for days left
        if (listOfPackages.get(index).getDeliveryStatus()) {
            daysLeftPrompt = "\n";

        } else {
            //If daysLeft is negative, then the package is overdue
            if (daysLeft < 0) {
                daysLeft *= (-1);//make it not negative
                daysLeftPrompt = "\nDelivery is overdue by " + daysLeft + " days\n";
            }
        }

        System.out.println("Package #" + (index + 1) + "\n" +
                listOfPackages.get(index).toString() + daysLeftPrompt);
    }

    public Package createPackage() throws NumberFormatException, DateTimeException {

        this.control = false;
        String packageType = TYPE_BOOK;//CHANGE


        //String Inputs
        String name;
        do {
            System.out.print("Enter the name of your package: ");
            name = input.nextLine();

            if (name.isEmpty() || name.isBlank()) {
                System.out.println("Name cannot be empty, please enter a name");
            } else {
                System.out.println();
                this.control = true;
            }
        } while (!this.control);

        System.out.print("Enter any notes for your package: ");
        String notes = input.nextLine();
        System.out.println();

        //Double Inputs
        double price = inputDoubleTryCatch("Enter the price for your package (in dollars): $",
                "Negative price does not exist! Try again.");

        double weight = inputDoubleTryCatch("Enter the weight of your package (in kg): ",
                "Negative weight does not exist! Try again.");

        //Integer Inputs for LocalDateTime
        int year = dateTryCatch("Enter the year of the expected delivery date: ", EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE);
        int month = dateTryCatch("Enter the month of the expected delivery date (1-12): ", year, EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE);
        int day = dateTryCatch("Enter the day of the expected delivery date (1-28/29/30/31): ", year, month, EMPTY_VALUE, EMPTY_VALUE);
        int hour = dateTryCatch("Enter the hour of the expected delivery date (0-23): ", year, month, day, EMPTY_VALUE);
        int minute = dateTryCatch("Enter the minute of the expected delivery date (0-59): ", year, month, day, hour);

        //LocalDateTime object will always be created properly, fields will always be valid
        LocalDateTime deliveryDate = LocalDateTime.of(year, month, day, hour, minute);

        System.out.println(name + " has been added to the list.");

        Package newPackage = pkgFactory.getInstance(packageType); //CHANGE!

        return newPackage;
    }

    public int getMenuInput() throws NumberFormatException {
        control = false;
        int userInput = 0;

        while (!control) {
            try {
                System.out.println("Choose a menu option by entering" +
                        " a whole number between 1 and 7.");
                System.out.print("Your input: ");
                userInput = Integer.parseInt(input.nextLine());

                if (userInput < 1 || userInput > 7) {
                    throw new NumberFormatException();
                }

                control = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Enter a number between 1 and 7");
            }
        }

        return userInput;
    }

    private double inputDoubleTryCatch(String question, String errorLine)
            throws NumberFormatException {
        double finalNumber = 0.0;
        this.control = false;

        while (!control) {
            System.out.print(question);
            try {
                finalNumber = Double.parseDouble(input.nextLine());

                if (finalNumber < 0) {
                    throw new NumberFormatException();
                }

                System.out.println();

                this.control = true;
            } catch (NumberFormatException nfe) {
                System.out.println(errorLine + '\n');
            }
        }

        return finalNumber;
    }

    private int dateTryCatch(String question, int year, int month, int day, int hour)
            throws DateTimeException {

        int inputValue = 0;
        control = false;

        while (!this.control) {
            System.out.print(question);
            try {
                inputValue = Integer.parseInt(input.nextLine());

                //if-else block tests inputValue for its correct test
                //LocalDate.of() and LocalDateTime.of() are used to test.
                if (year == EMPTY_VALUE) {
                    LocalDate.of(inputValue, 1, 1);
                } else if (month == EMPTY_VALUE) {
                    LocalDate.of(year, inputValue, 1);
                } else if (day == EMPTY_VALUE) {
                    LocalDate.of(year, month, inputValue);
                } else if (hour == EMPTY_VALUE) {
                    LocalDateTime.of(year, month, day, inputValue, 1);
                } else {
                    LocalDateTime.of(year, month, day, hour, inputValue);
                }

                System.out.println();
                control = true;

            } catch (DateTimeException dte) {
                System.out.println("Error: Invalid input, please try again.\n");
            }
        }
        return inputValue;
    }

    private boolean isOverdue(LocalDateTime packageDate) {
        return packageDate.isBefore(currentTime);
    }

} // TextMenu.java
