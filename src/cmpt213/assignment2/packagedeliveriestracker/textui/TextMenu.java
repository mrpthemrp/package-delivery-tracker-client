package cmpt213.assignment2.packagedeliveriestracker.textui;

import cmpt213.assignment2.packagedeliveriestracker.model.Package;
import cmpt213.assignment2.packagedeliveriestracker.model.PackageBase;
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

    public void listPackages(int menuOption, ArrayList<PackageBase> listOfPackages) {
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

    public void changeAPackage(ArrayList<PackageBase> packageList, String question,
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
                            if(packageNumber > packageList.size()){ //  chedckkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk
                                throw new NumberFormatException();
                            } else {
                                packageList.get(packageNumber).setDeliveryStatus(true);
                                System.out.println(packageList.get(packageNumber).getName() + " has been delivered.");
                                this.control = true;
                            }
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

    private void printSinglePackage(int index, ArrayList<PackageBase> listOfPackages) {

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

    public PackageBase createPackage() throws NumberFormatException, DateTimeException {

        this.control = false;
        //PackageType
        PackageFactory.PackageType packageType = PackageFactory.PackageType.BOOK;
        while (!control){
            int userInputPkgType = inputIntegerTryCatch(1,3,"Enter the type of package (" +
                    "1: Book, 2: Perishable, 3: Electronic): ","Input must be an integer between 1 and 3");

            if (userInputPkgType == 2) {
                packageType = PackageFactory.PackageType.PERISHABLE;

            } else if (userInputPkgType == 3) {
                packageType = PackageFactory.PackageType.ELECTRONIC;
            }
            control = true;
        }

        this.control = false;
        //String Inputs
        String name = inputStringTryCatch("name");

        System.out.print("Enter any notes for your package: ");
        String notes = input.nextLine();
        System.out.println();

        //Double Inputs
        double price = inputDoubleTryCatch("Enter the price for your package (in dollars): $",
                "Negative price does not exist! Try again.");

        double weight = inputDoubleTryCatch("Enter the weight of your package (in kg): ",
                "Negative weight does not exist! Try again.");

        //Integer Inputs for LocalDateTime
        System.out.println("\nFollowing inputted numbers are for EXPECTED delivery date.\n");
        int deliveryYear = dateSetterHelper("expected", EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE);
        int deliveryMonth = dateSetterHelper("expected", deliveryYear, EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE);
        int deliveryDay = dateSetterHelper("expected", deliveryYear, deliveryMonth, EMPTY_VALUE, EMPTY_VALUE);
        int deliveryHour = dateSetterHelper("expected", deliveryYear, deliveryMonth, deliveryDay, EMPTY_VALUE);
        int deliveryMinute = dateSetterHelper("expected", deliveryYear, deliveryMonth, deliveryDay, deliveryHour);


        //LocalDateTime object will always be created properly, fields will always be valid
        LocalDateTime deliveryDate = LocalDateTime.of( deliveryYear, deliveryMonth, deliveryDay, deliveryHour, deliveryMinute);

        //Set extra field
        String extraField = "";
        switch (packageType){
            case BOOK  -> extraField = inputStringTryCatch("author's name");
            case PERISHABLE -> {
                System.out.println("\nFollowing inputted numbers are for EXPIRY delivery date.\n");
                int expiryYear = dateSetterHelper("expiry", EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE);
                int expiryMonth = dateSetterHelper("expiry", expiryYear, EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE);
                int expiryDay = dateSetterHelper("expiry", expiryYear, expiryMonth, EMPTY_VALUE, EMPTY_VALUE);
                int expiryHour = dateSetterHelper("expiry", expiryYear, expiryMonth, expiryDay, EMPTY_VALUE);
                int expiryMinute = dateSetterHelper("expiry", expiryYear, expiryMonth, expiryDay, expiryHour);

                extraField = (expiryYear+","+expiryMonth+","+expiryDay+","+expiryHour+","+expiryMinute);
            }
            case ELECTRONIC  -> extraField = Double.toString(
                    inputDoubleTryCatch("Enter the environmental handling fee of your package (in dollars): $",
                    "Negative weight does not exist! Try again."));
        }

        //new package created
        PackageBase newPackage = pkgFactory.getInstance(packageType, name, notes, price, weight, deliveryDate, extraField);

        System.out.println(name + " has been added to the list.");

        return newPackage;
    }

    private String inputStringTryCatch(String nameType) {
        this.control = false;
        String name;
        do {
            System.out.print("Enter the "+nameType+" of your package: ");
            name = input.nextLine();

            if (name.isEmpty() || name.isBlank()) {
                System.out.println("Cannot be empty, please enter a "+nameType);
            } else {
                System.out.println();
                this.control = true;
            }
        } while (!this.control);

        return name;
    }


    public int inputIntegerTryCatch(int lowerBound, int upperBound, String question, String errorLine) throws NumberFormatException {
        control = false;
        int userInput = 0;

        while (!control) {
            try {
                System.out.println(question);
                System.out.print("Your input: ");
                userInput = Integer.parseInt(input.nextLine());

                if (userInput < lowerBound || userInput > upperBound) {
                    throw new NumberFormatException();
                }

                control = true;
            } catch (NumberFormatException nfe) {
                System.out.println(errorLine);
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

    private int dateSetterHelper (String dateType, int year, int month, int day, int hour){
        if (year == EMPTY_VALUE) {
            return dateTryCatch(("Enter the year of the "+dateType+" delivery date: "), year, month, day, hour);
        } else if (month == EMPTY_VALUE) {
            return dateTryCatch(("Enter the month of the "+dateType+" delivery date (1-12): "), year, month, day, hour);
        } else if (day == EMPTY_VALUE) {
            return dateTryCatch(("Enter the day of the "+dateType+" delivery date (1-28/29/30/31): "), year, month, day, hour);
        } else if (hour == EMPTY_VALUE) {
            return dateTryCatch(("Enter the hour of the "+dateType+" delivery date (0-23): "), year, month, day, hour);
        } else {
            return dateTryCatch(("Enter the minute of the "+dateType+" delivery date (1-59): "), year, month, day, hour);
        }
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
