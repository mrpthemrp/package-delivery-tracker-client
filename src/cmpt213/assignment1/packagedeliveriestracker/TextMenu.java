package cmpt213.assignment1.packagedeliveriestracker;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TextMenu {
    private String menuTitle;
    private ArrayList<String> menuOptions;

    public TextMenu(String menuTitle){
        this.menuTitle = menuTitle;

        //initialize ArrayList
        menuOptions = new ArrayList<>();
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void displayMenu(){
        System.out.println("displayMenu works!");
    }

    //read input method to implement
}
