package cmpt213.assignment1.packagedeliveriestracker;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TextMenu {
    private static final int LIST_PACKAGES = 1;
    private static final int LIST_OVERDUE_PACKAGES = 4;
    private static final int LIST_UPCOMING_PACKAGES = 5;
    private static final String NO_PACKAGE_MESSAGE = "No packages to show";
    private static final int EMPTY_VALUE = 0;
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

    //INPUT
    public int getMenuInput() throws NumberFormatException {
        control = false;
        int userInput = 0;

        while (!control) {
            try {
                System.out.println("Choose a menu option by entering" +
                        " a whole number between 1 and 7.");
                System.out.print("Your input: ");
                userInput = Integer.parseInt(input.nextLine());

                if (userInput < 0 || userInput > 7) {
                    throw new NumberFormatException();
                }

                control = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Enter a number between 1 and 7");
            }
        }

        return userInput;
    }

    private double inputTryCatch(String question, String errorLine) {
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
                System.out.println(errorLine);
            }
        }

        return finalNumber;
    }

    private int dateTryCatch(String question, int year, int month) {
        int value = 0;
        control = false;
        while (!this.control) {
            try {
                System.out.print(question);
                value = Integer.parseInt(input.nextLine());

                if (year == EMPTY_VALUE) {
                    LocalDate.of(value, 1, 1);
                } else if (month == EMPTY_VALUE) {
                    LocalDate.of(year, value, 1);
                } else {
                    LocalDate.of(year, month, value);
                }

                System.out.println();
                control = true;
            } catch (DateTimeException dte) {
                System.out.println("Error: this date does not exist.");
            }
        }
        return value;
    }

    private LocalDateTime finalDateTryCatch(int year, int month, int day) {
        LocalDateTime finalDate = LocalDateTime.now();
        int hour = EMPTY_VALUE;
        int minute = EMPTY_VALUE;
        control = false;

        while (!control) {
            try {
                if (hour == EMPTY_VALUE) {
                    System.out.print("Enter the hour of the expected delivery date (0-23): ");
                    hour = Integer.parseInt(input.nextLine());
                    finalDate = LocalDateTime.of(year, month, day, hour, 1);
                }
                if (minute == EMPTY_VALUE) {
                    System.out.print("Enter the minute of the expected delivery date (0-59): ");
                    minute = Integer.parseInt(input.nextLine());
                    finalDate = LocalDateTime.of(year, month, day, hour, minute);
                }

                System.out.println();
                control = true;
            } catch (DateTimeException dte) {
                if (hour != EMPTY_VALUE) {
                    hour = EMPTY_VALUE;
                }

                if (minute != EMPTY_VALUE) {
                    minute = EMPTY_VALUE;
                }
                System.out.println("Error: this time does not exist.");
            }
        }

        return finalDate;
    }

    public Package createPackage() throws NumberFormatException, DateTimeException {

        control = false;
        String name;
        do {
            System.out.print("Enter the name of your package: ");
            name = input.nextLine();

            if (name.isEmpty() || name.isBlank()) {
                System.out.println("name cannot be empty, please enter a name");
            } else {
                System.out.println();
                this.control = true;
            }
        } while (!this.control);

        System.out.print("Enter any notes for your package: ");
        String notes = input.nextLine();
        System.out.println();

        double price = inputTryCatch("Enter the price for your package (in dollars): $",
                "Negative price does not exist! Try again.");

        double weight = inputTryCatch("Enter the weight of your package (in kg): ",
                "Negative weight does not exist! Try again.");

        int year = dateTryCatch("Enter the year of the expected delivery date: ", EMPTY_VALUE, EMPTY_VALUE);
        int month = dateTryCatch("Enter the month of the expected delivery date (1-12): ", year, EMPTY_VALUE);
        int day = dateTryCatch("Enter the day of the expected delivery date (1-28/29/30/31): ", year, month);

        LocalDateTime deliveryDate = finalDateTryCatch(year, month, day);

        System.out.println(name + " has been added to the list.");
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
                    int packageCount = 0;
                    if (menuOption == LIST_OVERDUE_PACKAGES) {
                        if (isOverdue(listOfPackages.get(i).getExpectedDeliveryDate())) {
                            packageCount++;
                            printSinglePackage(i, listOfPackages);
                        }

                    } else if (menuOption == LIST_UPCOMING_PACKAGES) {
                        if (!isOverdue(listOfPackages.get(i).getExpectedDeliveryDate())) {
                            packageCount++;
                            printSinglePackage(i, listOfPackages);
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

    // TODO: 2022-05-28
    /* - create list of packages not delivered (by boolean)
        - finish case 6
        - debug case 3,4,5
    */
    public void changeAPackage(ArrayList<Package> packageList, String question,
                               String prompt, int listType) throws NumberFormatException {
        if (packageList.size() == 0) {
            System.out.println(NO_PACKAGE_MESSAGE);
            return;
        }

        this.control = false;
        do {
            try {
                System.out.println("\nList of packages:");
                listPackages(listType, packageList);
                System.out.println();

                System.out.println(question + "\nEnter 0 to cancel.");
                System.out.print(prompt);
                int packageNumber = Integer.parseInt(input.nextLine());

                if (packageNumber == 0) {
                    System.out.println("Cancel selected.\nReturning to Main Menu.");
                    control = true;
                }

                packageNumber--; //to get package index
                if (packageNumber >= 0 && packageNumber < packageList.size()) {
                    System.out.println("Removed Package #" + (packageNumber + 1));
                    packageList.remove(packageNumber);
                    this.control = true;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Package does not exist, try again.");
            }
        } while (!control);
    }
} // TextMenu.java
