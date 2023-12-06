import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

//I've supressed the warnings for the time being. It complains about the checkfile method
//and unchecked casts.
//I also have to figure out a better way to display saved contacts and optimize the method
//to checking the file. My reliance on arraylist and ObjectStream may be a bit of a problem
@SuppressWarnings("unchecked")
public class ContactsApp implements Serializable {
    //File systems and OOS is private static so they will be the same for each method
    //It also wont work without them being so
    public static ArrayList<Contact> contactsList = new ArrayList<Contact>();//<--- This is where the unchecked casts warning is coming from
    public static File contacts = new File("contacts.txt");                  //All of this 'just works' -Todd Howard, 2016
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;
    public static ListIterator lister;
    


    public static void main(String [] args) throws Exception {
        Console console = System.console();
        Boolean shutDown = false;
        checkFile();

        System.out.println("Welcome!");
        System.out.println("Please input your desired choice and press enter");
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
                    addContact();
                    break;
                case "3":
                    System.out.println("Not yet implemented.");
                    break;
                case "4":
                    System.out.println("Not yet implemented.");
                    break;
                case "5":
                    shutDown = true;
                    break;
                default:
                    System.out.println("Invalid input. Please try again");
                    break;
            }
        }
    }
    public static void checkFile() throws Exception {
        if(contacts.isFile()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(contacts));
                contactsList = (ArrayList<Contact>)ois.readObject();
                ois.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void addContact() {
        //Add contacts method will add up all the required values, which then OOS will
        //write to the file
        System.out.println("Give the ID of the contact");
        String id = System.console().readLine();
        System.out.println("Give the first name");
        String firstName = System.console().readLine();
        System.out.println("Give the last name");
        String lastName = System.console().readLine();
        System.out.println("Give the phone number");
        String phoneNumber = System.console().readLine();
        System.out.println("(Opt.)Give the address");
        String address = System.console().readLine();
        System.out.println("(Opt.) Give the email");
        String email = System.console().readLine();
        System.out.println("Contact added!");
        contactsList.add(new Contact(id, firstName, lastName, phoneNumber, address, email));
        try{
            oos = new ObjectOutputStream(new FileOutputStream(contacts));
            oos.writeObject(contactsList);
            oos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readContact() {
        //readContact method will read all the contacts within the file. 
        //However, not directly from the file and rather from the arraylist that works as an intermediary
        lister = contactsList.listIterator();
        boolean exit = false;
        int capacity = contactsList.size();
        while (!exit) {
            System.out.println("1. View all");
            System.out.println("2. Search");
            System.out.println("3. Back");
            String readChoice = System.console().readLine();
            switch (readChoice) {
                //View all will take the amount of objects stored and executes a for loop to use the 
                //listIterator to print out the saved contacts.
                //If the file is missing or empty, the program will also tell you that.
                case "1":
                    System.out.println("-------------------");
                    while(lister.hasNext()) {
                        System.out.println(lister.next());
                    }
                    if(capacity == 0) {
                        System.out.println("Your list is empty, or the file is missing");
                    }
                    //This will return the listIterator to the first index so it doesnt bug out of range
                    //Once the user asks for the contacts again
                    System.out.println("--------------------");
                    lister = contactsList.listIterator(0);
                    System.out.println("Press enter to continue");
                    String userConf = System.console().readLine();
                    //This console prompt is just there to allow the user to go through with their own pace
                    //It will not print out the menu until the user presses enter
                    break;
                case "2":
                    //Search does not work yet for some reason. That has to be solved
                    //in order to get deleting and editing working
                    boolean found = false;
                    System.out.println("Enter the first name of the contact");
                    String id = System.console().readLine();
                    System.out.println("--------------------");
                    lister = contactsList.listIterator();
                    while(lister.hasNext()) {
                        Contact searchInput = (Contact)lister.next();
                        if (searchInput.id == id) {
                            System.out.println(searchInput);
                            found = true;
                        }
                    }
                    if(!found) {
                        System.out.println("Searching does not work yet.");
                    }
                    if(capacity == 0) {
                        System.out.println("Your list is empty, or the file is missing");
                    }
                    System.out.println("--------------------");
                    System.out.println("Press enter to continue");
                    lister = contactsList.listIterator(0);
                    String userConf2 = System.console().readLine();
                    break;
                case "3":
                    exit = true;
                    break;
                default :
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
    public void deleteContact() {
    }
    public void editContact() {
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
//
//
//6.12.2023
//Great success! I finally have a good gameplan for the whole project and made major strides. The only points
//of concern and refining now is further control over the contacts and getting user validation done.
//I highly doubt I can make it for every single input, but at least the ID and both first and lastnames
//