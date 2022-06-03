package cmpt213.assignment1.packagedeliveriestracker;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TextMenu class ...
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
    private final LocalDateTime currentTime;
    private final DateTimeFormatter monthDateYear;
    private final Scanner input = new Scanner(System.in);
    private boolean control;

    /**
     * Constructor for class
     *
     * @param menuTitle
     */
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

    /**
     *
     * @return
     * @throws NumberFormatException
     */
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

    /**
     *
     * @param question
     * @param errorLine
     * @return
     */
    private double inputDoubleTryCatch(String question, String errorLine) {
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

    /**
     *
     * @param question
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     */
    private int dateTryCatch(String question, int year, int month, int day, int hour) {
        int value = 0;
        control = false;
        while (!this.control) {
            System.out.print(question);
            try {
                value = Integer.parseInt(input.nextLine());

                if (year == EMPTY_VALUE) {
                    LocalDate.of(value, 1, 1);
                } else if (month == EMPTY_VALUE) {
                    LocalDate.of(year, value, 1);
                } else if (day == EMPTY_VALUE) {
                    LocalDate.of(year, month, value);
                } else if (hour == EMPTY_VALUE) {
                    LocalDateTime.of(year, month, day, value, 1);
                } else {
                    LocalDateTime.of(year, month, day, hour, value);
                }

                System.out.println();
                control = true;
            } catch (DateTimeException dte) {
                System.out.println("Error: Invalid input, please try again.\n");
            }
        }
        return value;
    }

    /**
     *
     * @return
     * @throws NumberFormatException
     * @throws DateTimeException
     */
    public Package createPackage() throws NumberFormatException, DateTimeException {

        this.control = false;
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

        double price = inputDoubleTryCatch("Enter the price for your package (in dollars): $",
                "Negative price does not exist! Try again.");

        double weight = inputDoubleTryCatch("Enter the weight of your package (in kg): ",
                "Negative weight does not exist! Try again.");

        int year = dateTryCatch("Enter the year of the expected delivery date: ", EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE);
        int month = dateTryCatch("Enter the month of the expected delivery date (1-12): ", year, EMPTY_VALUE, EMPTY_VALUE, EMPTY_VALUE);
        int day = dateTryCatch("Enter the day of the expected delivery date (1-28/29/30/31): ", year, month, EMPTY_VALUE, EMPTY_VALUE);
        int hour = dateTryCatch("Enter the hour of the expected delivery date (0-23): ", year, month, day, EMPTY_VALUE);
        int minute = dateTryCatch("Enter the minute of the expected delivery date (0-59): ", year, month, day, hour);

        LocalDateTime deliveryDate = LocalDateTime.of(year, month, day, hour, minute);

        System.out.println(name + " has been added to the list.");
        return new Package(name, notes, price, weight, deliveryDate);
    }

    /**
     *
     */
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

    /**
     *
     * @param option
     */
    public void printMenuOption(int option) {
        System.out.println("\n" + menuOptions.get(option));
    }

    /**
     *
     * @param index
     * @param listOfPackages
     */
    private void printSinglePackage(int index, ArrayList<Package> listOfPackages) {
        System.out.println("Package #" + (index + 1) + "\n" +
                listOfPackages.get(index).toString() + "\n");
    }

    /**
     *
     * @param menuOption
     * @param listOfPackages
     */
    public void listPackages(int menuOption, ArrayList<Package> listOfPackages) {
        if (listOfPackages.size() == 0) {
            System.out.println(NO_PACKAGE_MESSAGE);
        } else {
            int packageCount = 0;
            for (int i = 0; i < listOfPackages.size(); i++) {
                Package pkg = listOfPackages.get(i);
                if (menuOption == LIST_PACKAGES) {
                    packageCount++;
                    printSinglePackage(i, listOfPackages);

                } else {
                    if (menuOption == LIST_OVERDUE_PACKAGES && !pkg.getDeliveryStatus()) {
                        if (isOverdue(pkg.getExpectedDeliveryDate())) {
                            packageCount++;
                            printSinglePackage(i, listOfPackages);
                        }

                    } else if (menuOption == LIST_UPCOMING_PACKAGES) {
                        if (!isOverdue(pkg.getExpectedDeliveryDate()) && !pkg.getDeliveryStatus()) {
                            packageCount++;
                            printSinglePackage(i, listOfPackages);
                        }
                    } else if (menuOption == LIST_UNDELIVERED_PACKAGES) {
                        if (!pkg.getDeliveryStatus()) {
                            packageCount++;
                            printSinglePackage(i, listOfPackages);
                        }
                    }
                }
            }
            if (packageCount == 0) {
                System.out.println(NO_PACKAGE_MESSAGE);
            }
        }
    }

    /**
     *
     * @param packageDate
     * @return
     */
    private boolean isOverdue(LocalDateTime packageDate) {
        return packageDate.isBefore(currentTime);
    }

    /**
     *
     * @param packageList
     * @param question
     * @param prompt
     * @param listType
     * @throws NumberFormatException
     */
    public void changeAPackage(ArrayList<Package> packageList, String question,
                               String prompt, int listType) throws NumberFormatException {
        if (packageList.size() == 0) {
            System.out.println(NO_PACKAGE_MESSAGE);
        } else {
            this.control = false;
            while (!this.control) {

                System.out.println("\nList of packages:");
                listPackages(listType, packageList);
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

                        if (listType == LIST_PACKAGES) {
                            if (packageNumber >= 0 && packageNumber < packageList.size()) {
                                System.out.println(packageList.get(packageNumber).getName() + " has been removed from the list.");
                                packageList.remove(packageNumber);
                                this.control = true;
                            } else {
                                throw new NumberFormatException();
                            }
                        } else if (listType == LIST_UNDELIVERED_PACKAGES) {
                            packageList.get(packageNumber).setDeliveryStatus(true);
                            System.out.println(packageList.get(packageNumber).getName() + " has been delivered.");
                            this.control = true;
                        }
                    }

                } catch (NumberFormatException nfe) {
                    System.err.println("Package does not exist, try again.");
                }
            }
        }
    }

} // TextMenu.java
