package cmpt213.assignment1.packagedeliveriestracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextMenu {
    private static final int LIST_PACKAGES = 1;
    private static final int LIST_OVERDUE_PACKAGES = 4;
    private static final int LIST_UPCOMING_PACKAGES = 5;
    private String menuTitle;
    private ArrayList<String> menuOptions;
    private LocalDateTime currentTime;
    private DateTimeFormatter monthDateYear;
    private Scanner input = new Scanner(System.in);

    //MENU
    public TextMenu(String menuTitle) {
        this.menuTitle = menuTitle;
        this.currentTime = LocalDateTime.now();
        monthDateYear = DateTimeFormatter.ofPattern("MMM dd, yyyy");

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

    public String getMenuTitle() {
        return menuTitle;
    }

    public void displayMenu(){
        String hashTags ="####";// to account for spaces
        for(int i =0;i<menuTitle.length();i++){
            hashTags+="#";
        }
        System.out.println(hashTags+"\n# "+menuTitle+" #\n"+hashTags);
        System.out.println("Today is: "+currentTime.format(monthDateYear));
        for (int j =0;j< menuOptions.size();j++){
            System.out.println((j+1)+": "+menuOptions.get(j));
        }
    }

    public void printMenuOption(int option){
        System.out.println(menuOptions.get(option));
    }

    public int getMenuInput() throws InputMismatchException{
        boolean correctInput = false;
        int userInput = 0;

        while(!correctInput){
            try{
                System.out.println("Choose a menu option by entering" +
                        " a whole number between 1 and 7.");
                System.out.print("Your input: ");
                userInput = input.nextInt();

                if (userInput > 0 && userInput <=7){
                    correctInput = true;
                }
                else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid input. Enter a number between 1 and 7");
                input.next();
            }
        }

        return userInput;
    }

    //INPUT AND OPTIONS?
    public Package createPackage (){

        System.out.print("Enter the name of your package: ");
        String name = input.next();
        System.out.println();

        System.out.print("Enter any notes for your package: ");
        String notes = input.next();
        System.out.println();

        System.out.print("Enter the price for your package (in dollars): $");
        double price = input.nextDouble();
        System.out.println();

        System.out.print("Enter the weight of your package (in kg): ");
        double weight = input.nextDouble();
        System.out.print("kg\n");

        System.out.print("Enter the year of the expected delivery date: ");
        int year = input.nextInt();
        System.out.println();

        System.out.print("Enter the month of the expected delivery date (1-12): ");
        int month = input.nextInt();
        System.out.println();

        System.out.print("Enter the day of the expected delivery date (1-28/29/30/31): ");
        int day = input.nextInt();
        System.out.println();

        System.out.print("Enter the hour of the expected delivery date (0-23): ");
        int hour = input.nextInt();
        System.out.println();

        System.out.print("Enter the minute of the expected delivery date (0-59): ");
        int minute = input.nextInt();
        System.out.println();

        LocalDateTime deliveryDate = LocalDateTime.of(year,month, day, hour, minute);

        return new Package(name, notes, price, weight, deliveryDate);
    }

    private void printSinglePackage(int index,ArrayList<Package> listOfPackages ){
        System.out.println("Package #"+(index+1));
        listOfPackages.get(index).printPackageInfo();
        System.out.println();
    }

    private boolean isOverdue(LocalDateTime packageDate){
        if(packageDate.isAfter(currentTime)){
            return true;
        }

        return false;
    }

    //QuickSort reference from: https://www.geeksforgeeks.org/quick-sort/

    private void quickSortSwap (ArrayList<Package> packageList, int i, int j){
        Package temp = packageList.get(i);
        packageList.set(i, packageList.get(j));
        packageList.set(j, temp);
    }

    private int quickSortPartition(ArrayList<Package> packageList, int low, int high){
        //set pivot
        LocalDateTime pivot = packageList.get(high).getExpectedDeliveryDate();

        int i = (low -1);

        for(int j =low; j <=(high-1); j++){
            if(packageList.get(j).getExpectedDeliveryDate().isBefore(pivot)){
                i++;
                quickSortSwap(packageList, i, j);
            }
        }
        quickSortSwap(packageList, i+1, high);
        return(i+1);
    }

    //sorts by LocalDateTime field!
    private void quickSortPackageList (ArrayList<Package> unsortedList, int low, int high){
        if(low < high){
            int partitionIndex = quickSortPartition(unsortedList, low, high);

            quickSortPackageList(unsortedList, low, partitionIndex-1);
            quickSortPackageList(unsortedList, partitionIndex+1, high);
        }
    }

    public void listPackages(int menuOption,ArrayList<Package> listOfPackages ){
        if(listOfPackages.size() ==0){
            System.out.println("No packages to show.");
        } else{
            ArrayList<Package> sortedList = listOfPackages;
            quickSortPackageList(sortedList, 0, sortedList.size()-1);
            for(int i =0;i< listOfPackages.size();i++){

                if(menuOption == LIST_PACKAGES){
                    printSinglePackage(i, listOfPackages);

                } else if (menuOption == LIST_OVERDUE_PACKAGES){
                    if(isOverdue(sortedList.get(i).getExpectedDeliveryDate())){
                        sortedList.get(i).printPackageInfo();
                    }

                } else if (menuOption == LIST_UPCOMING_PACKAGES) {
                    if(!isOverdue(sortedList.get(i).getExpectedDeliveryDate())){
                        sortedList.get(i).printPackageInfo();
                    }
                }
            }
        }

    }
}
