package cmpt213.assignment1.packagedeliveriestracker;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextMenu {
    private String menuTitle;
    private ArrayList<String> menuOptions;
    private Scanner input = new Scanner(System.in);

    public TextMenu(String menuTitle){
        this.menuTitle = menuTitle;

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
        for(int i =0;i<menuOptions.size();i++){
            hashTags+="#";
        }
        System.out.println(hashTags+"\n# "+menuTitle+" #\n"+hashTags);
        System.out.println("Today is: DATE (to be implemented)");
    }

    public int getInput() throws InputMismatchException{
        boolean correctInput = false;
        int userInput = 0;

        while(!correctInput){
            try{
                userInput = input.nextInt();
                correctInput = true;
            } catch (InputMismatchException e){
                System.out.println("Incorrect input. Please try again.");
                input.next();
            }
        }

        return userInput;
    }
}
