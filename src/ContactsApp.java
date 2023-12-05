import java.io.*;
import java.util.ArrayList;

public class ContactsApp implements Serializable {
    //File systems and OOS is private static so they will be the same for each method
    //It also wont work without them being so
    public static ArrayList<Contact> contactsList = new ArrayList<Contact>();
    public static File contacts = new File("contacts.txt");
    public static ObjectOutputStream oos;


    public static void main(String [] args) {
        Console console = System.console();
        Boolean shutDown = false;
        Contact newContact = new Contact(null, null, null, null, null, null);
        //Start of the main method calls all essential methods from all other classes 
        
        System.out.println("Welcome!");
        System.out.println("Please input your desired choice with a number");
        //A simple while loop I don't foresee changing soon.
        while(!shutDown) {
            System.out.println("1. View contacts");
            System.out.println("2. Add contacts");
            System.out.println("3. Edit contacts");
            System.out.println("4. Delete contacts");
            System.out.println("5. Exit");
            String userChoice = System.console().readLine();
            switch (userChoice) {
                case "1":
                    readContact();
                    break;
                case "2":
                    addContact(newContact);
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    shutDown = true;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
    public static void readContact() {
        System.out.println(contactsList);
        System.out.println("Enter anything to continue");
        String readChoice = System.console().readLine();
        switch (readChoice) {
            default :
                return;
        }
    }
    public static void addContact(Contact a) {
        System.out.println("Give the ID of the contact");
        a.id = System.console().readLine();
        System.out.println("Give the first name");
        a.firstName = System.console().readLine();
        System.out.println("Give the last name");
        a.lastName = System.console().readLine();
        System.out.println("Give the phone number");
        a.phoneNumber = System.console().readLine();
        System.out.println("(Opt.)Give the address");
        a.address = System.console().readLine();
        System.out.println("(Opt.) Give the email");
        a.email = System.console().readLine();
        System.out.println("Contact added!");
        contactsList.add(new Contact(a.id,a.firstName,a.lastName,a.phoneNumber,a.address,a.email));
        try{
            oos = new ObjectOutputStream(new FileOutputStream(contacts));
            oos.writeObject(contactsList);
            oos.close();
        }catch (IOException e) {
            e.printStackTrace();
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

//29.11.2023
//I managed to figure out how to create files and made a method that creates it if not present
//However, getting the contacts added there is a new hiccup since the object referred here
//doesn't change depending on how many people were saved on the file, so there effectively is just one
//contact slot. (And unreadable at that)
//I need to make a method that scans the file for objects and creates the contact in a way that
//increments numbers into the creation
//

//5.12.2023
//After so much fighting with the code, and very shoddy construction, I decided to start the build again
//from the starting draft. I renamed all files, classes, etc. more clearly to help make it more readable
//and moved all tenous functionality to the contactsApp class in order to make it easier to compile and
//execute the code.
//