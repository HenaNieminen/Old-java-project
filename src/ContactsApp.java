import java.io.Console;

public class ContactsApp {
    public static void main(String [] args) {
        Console c = System.console();
        Boolean shutDown = false;

        while(!shutDown) {
            System.out.println("Welcome!");
            System.out.println("Please input your desired choice with a number");
            System.out.println("1. View contacts, 2. Add contacts, 3. Edit or Delete contacts, 4. Exit");
        
            String userC = System.console().readLine();
            switch (userC) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    shutDown = true;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}

//20.11.2023
//The main hub of the code which will call all classes and methods and execute them for the user
//Possible changes coming to the structure and potential expansions for classes when necessary
//The basis of the user selection will be done with a switch case that will run until user decides
//to stop. The empty selections will call the respective classes and methods once done.
//Decided to use string instead of int for the user input to avoid making too much of a mess with exception
//catching. If it gets hairy down the line, I will probably change things around.