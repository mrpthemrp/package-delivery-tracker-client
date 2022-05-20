package cmpt213.assignment1.packagedeliveriestracker;

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
        for(int i =0;i<menuTitle.length();i++){
            hashTags+="#";
        }
        System.out.println(hashTags+"\n# "+menuTitle+" #\n"+hashTags);
        System.out.println("Today is: DATE (to be implemented)");
        for (int j =0;j< menuOptions.size();j++){
            System.out.println((j+1)+": "+menuOptions.get(j));
        }
    }

    public int getInput() throws InputMismatchException{
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
}
