import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * Class ContactsApp is the main class used for the functionalities of the app
 * @author Henri Nieminen
 * The public variables include:
 * @param contactsList arraylist is used as a backbone for the contact management
 * @param contacts creates the file for saving contacts into. It will be created in the source directory.
 * @param oos is the objectOutputStream used to write into the file
 * @param ois is the objetInputStream used to read from the file and loads it into the arraylist
 * @param lister is a ListIterator object used to browse through the contactsList
 * @param found is a boolean value used for searching, editing and deleting. 
 * @param editIndex is an integer value used to keep track of which index should the editing methods edit. 
 * It's found in the select method and exists solely to keep the editing and deleting informed
 */
//I've supressed the warnings for the single unchecked operation
@SuppressWarnings("unchecked")
public final class ContactsApp implements Serializable {
    //File systems and OOS is private static so they will be the same for each method
    public static ArrayList<Contact> contactsList = new ArrayList<Contact>();//<--- This is where the unchecked casts warning is coming from
    public static File contacts = new File("contacts.txt");                  //"All of this 'just works' " -Todd Howard, 2016
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;
    public static ListIterator lister;
    public static boolean found;
    public static int [] editArray = new int[1];
    public static int editIndex;

    /**
     * main method is used for calling methods to activate different functions
     * @param args are unused.  
     * @throws Exception in accordance with the checkFile method to handle runtime exceptions
     * @param console is the system console used to handle user inputs
     * @param shutDown is used to exit the main loop of the program
     * @param userChoice is used to take the user input for the selection
     */
    public static void main(String [] args) throws Exception {
        /**
         * The main method calls all respective methods for viewing, adding, editing and deleting contacts.
         * It uses a while loop and a boolean value to control the main loop and uses a switch case for the
         * user menu. The user will use the numerical keys to control the functions of the program, and they
         * in turn in the code will call the methods.
         */
        Console console = System.console();
        Boolean shutDown = false;
        checkFile();
        /**
         * The checkFile method is called within the main method to see if the file is intact and will load
         * the contents into the arraylist for saved contacts
         */
        System.out.println("Welcome!");
        System.out.println("Please input your desired choice and press enter");
        while (!shutDown) {
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
                    editContact();
                    break;
                case "4":
                    deleteContact();
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
    /**
     * userConfirm has a blank console readline only used to pace the program for the user
     */
    public static void userConfirm() {
        System.out.println("Press enter to continue");
        System.console().readLine();
    }
    /**
     * checkFile method is used to check through the saved file
     * @throws Exception for runtime exceptions
     * @param contacts is used here to check the file integrity and to load the contents
     * to the arraylist
     */
    public static void checkFile() throws Exception {
        /**
         * The checkFile will check if the file exists and will proceeed to load the file
         * into the arraylist
         */
        if (contacts.isFile()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(contacts));
                contactsList = (ArrayList<Contact>)ois.readObject();
                ois.close();
            } catch (IOException e) {
                /**
                 * It will catch any IOExceptions and will notify the user if their file is corrupt.
                 * This will often happen if the file is tampered with.
                 */
                //I replaced the stacktrace with a dialogue that just says 'you done goofed'
                System.out.println("Your file is corrupt. Your previously saved contacts are lost");
                System.out.println("To generate a new file, add a new contact");
                userConfirm();
            }
        }
    }
    /**
     * writeToFile method is used in editing and adding a contact
     * @param oos is used to write to the file
     */
    public static void writeToFile() {
        /**
         * writeToFile method is used to compress the writing to the file by calling it within methods
         * when it is needed. It will use the predefined ObjectOutPut stream declared in the class.
         */
        //Compressess the code to not have OOS everywhere separately
        try {
            oos = new ObjectOutputStream(new FileOutputStream(contacts));
            oos.writeObject(contactsList);
            oos.close();
        } catch (IOException e) {
            /**
             * In the case of an IOException, the program will print the stack trace and also notify
             * the user that an error has occured
             */
            e.printStackTrace();
            System.out.println("An unexpected error has occurred");
            //Might be necessary to still have a stack trace here
            //I cleared out all the unnecessary throws when it can just be declared here and print
            //the stack trace
        }
    }
    /**
     * addContact is the method for adding contacts
     * @param id the id of the created Contact object
     * @param firstName the first name of the created Contact object
     * @param lastName the last name of the created Contact object
     * @param phoneNumber the phone number of the created Contact object
     * @param address the address of the created Contact object
     * @param email the email address of the created Contact object
     * @param contactsList is written here with the new added contact
     */
    public static void addContact() {
        /**
         * This method will use the input methods for user validation and then create
         * an object into the file using the writeToFile method.
         */
            String id = "";
            String firstName = "";
            String lastName = "";
            String phoneNumber = "";
            String address = "";
            String email = "";
            System.out.println("Give the ID of the contact");
            id = idInput(id);
            System.out.println("Give the first name");
            firstName = firstNameInput(firstName);
            System.out.println("Give the last name");
            lastName = lastNameInput(lastName);
            System.out.println("Give the phone number");
            phoneNumber = phoneNumberInput(phoneNumber);
            System.out.println("(Opt.)Give the address");
            address = addressInput(address);
            System.out.println("(Opt.) Give the email");
            email = emailInput(email);
            System.out.println("Contact added!");
            contactsList.add(new Contact(id, firstName, lastName, phoneNumber, address, email));
            writeToFile();
            System.out.println("-----------------");
            userConfirm();
    }
    /**
     * readContact method is used to read from the filled arraylist
     * @param exit is used to control the loop for displaying the read menu.
     * @param readChoice is used to control the switch for different options
     * @param lister is reset to the first index here in order to function properly
     * @param contactsList is referred in listIterator objects method
     */
    public static void readContact() {
        //readContact method will read all the contacts within the file. 
        //However, not directly from the file and rather from the arraylist that works as an intermediary
        lister = contactsList.listIterator();
        boolean exit = false;
        while (!exit) {
            System.out.println("1. View all");
            System.out.println("2. Search");
            System.out.println("3. Back");
            String readChoice = System.console().readLine();
            switch (readChoice) {
                //In order to use the functionalities in the edit and delete method, I made them into separate methods
                //so they could be reused effectively.
                case "1":
                    //This will return the listIterator to the first index so it doesnt bug out of range
                    //I realized that if it was inside the method, it could possibly cause trouble for the editing and deleting functions
                    lister = contactsList.listIterator(0);
                    viewAll();
                    userConfirm();
                    break;
                case "2":
                    lister = contactsList.listIterator(0);
                    select();
                    userConfirm();
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
    /**
     * viewAll method is used for viewing contacts loaded into the arraylist
     * 
     * @param capacity is the size taken from the arraylist for contacts. 
     * Used to determine if the arraylist is empty or the file is missing completely
     * @param lister is used here to go through the whole contact arraylist and print contacts out.
     * @param conactsList is used in conjunction with the listIterator object lister
     */
    public static void viewAll() {
        /**
         * The viewAll method will go through the arraylist where the contacts are saved and will
         * print them all out for the user to see
         */
        int capacity = contactsList.size();
        int indexTrack = 0;
        /**
         * It uses a listIterator object predefined classwide for use in other methods as well.
         * It also keeps track of the capacity of the list and will notify the user if the list
         * is empty or missing.
         */
        System.out.println("-------------------");
        while (lister.hasNext()) {
            System.out.println(indexTrack + ": " + lister.next());
            indexTrack++;
        }
        if (capacity == 0) {
            System.out.println("Your list is empty, or the file is missing");
        }
        System.out.println("--------------------");
    }
    /**
     * select method is used for the user to search from the list
     * @param lister is used to go through the arraylist
     * @param capacity is used to check the size of the contactslist as in the viewAll method
     * @param searchName is used to search for the contact by its first name
     * @param searchInput is a Contact object that goes through the arraylist
     * @param exit is used to control the while loop for the console input
     * @param indexTrack is used to amount the index where the target contact is
     * @param editIndex indexTrack is then applied to this value
     */
    public static void select() {
        lister = contactsList.listIterator();
        int userSelect = 0;
        Console console = System.console();
        int capacity = contactsList.size();
        String searchName = "";
        //Index track will be used to see at which part of the arraylist the select method is at
        int indexTrack = 0;
        int arrayTrack = 0;
        int [] editArray = new int[capacity];
        found = false;
        Boolean exit = false;
        while (!exit) {
            if (capacity == 0) {
                System.out.println("--------------------");
                System.out.println("Your list is empty, or the file is missing");
                exit = true;
            } else {
                System.out.println("Enter the first name of the contact");
                searchName = System.console().readLine();
                System.out.println("--------------------");
                exit = true;
            }
        }
        while (lister.hasNext()) {
            //It will use the getter method on the 'quasi' object that goes throgh the list
            //and compares it with the user given input.
            Contact searchInput = (Contact)lister.next();
            if (searchInput.getFirstName().equals(searchName)) {
                System.out.println(indexTrack + ": " + searchInput);
                for (int i = 0; i < capacity; i++) {
                    editArray[i] += indexTrack;
                    editIndex = indexTrack;
                    found = true;
                }
                arrayTrack++;
                //Edit index is used for the edit and delete functions to delete or edit objects stored
                //at the specific index
            }
            indexTrack++;
        }
        if (arrayTrack > 1) {
            System.out.println("--------------------");
            System.out.println("Which " + searchName + " do you want to select? Enter their index on the list");
            try {
                editIndex = Integer.parseInt(console.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Give the number of which contact to select");
            }
        }
        if (!found) {
            System.out.println("Contact not found.");
        }
        System.out.println("--------------------");
    }
    /**
     * deleteContact method is used for the user to delete something from the arraylist and file
     * @param Console is there to read the integer of the index given by the user
     * @param lister is reset to the first index of the arraylist
     * @param exit is used to control the while loop that asks the user which contact to delete
     * @param contactsList uses the remove method with the editIndex as a parameter
     */
    public static void deleteContact() {
        //Added the view all method so the user can see all available contacts for deletion
        lister = contactsList.listIterator(0);
        boolean exit = false;
        viewAll();
        if (contactsList.size() == 0) {
            System.out.println("No contacts to delete. Press enter to continue");
            String userConfExit = System.console().readLine();
            exit = true;
        }
        select();
        while (!exit) {
            if (found) {
                System.out.println("Are you sure you want to delete this contact? y/n?");
                String userChoice = System.console().readLine();
                switch (userChoice.toLowerCase()) {
                    case "y":
                        contactsList.remove(editIndex);
                        writeToFile();
                        System.out.println("Contact deleted");
                        System.out.println("---------------");
                        userConfirm();
                        exit = true;
                        break;
                    case "n":
                        System.out.println("Deletion aborted");
                        System.out.println("---------------");
                        userConfirm();
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            } else {
                System.out.println("The contact you entered did not exist. Press enter to continue");
                String userConf = System.console().readLine();
                exit = true;
            }
        }
    }
    /**
     * editContact method is used for editing saved contacts in the arraylist and file
     * @param exit used to control the loop for editing
     * @param newId for inputting a new ID number
     * @param newFirst for inputting a new first name
     * @param newLast for inputting a new last name
     * @param newPhone for inputting a new phone number
     * @param newAddress for inputting a new address
     * @param newEmail for inputting a new email address
     */
    public static void editContact() {
        //Zeroes the listIterator index in order to not get stuck into one contact
        lister = contactsList.listIterator(0);
        boolean exit = false;
        //ditto
        viewAll();
        if (contactsList.size() == 0) {
            System.out.println("No contacts to edit. Press enter to continue");
            String userConfExit = System.console().readLine();
            exit = true;
        } else {
            select();
        }
        while (!exit) {
            if (found) {
                System.out.println("How do you want to edit this contact?");
                System.out.println(contactsList.get(editIndex));
                System.out.println("---------------");
                System.out.println("1. ID, 2. First name, 3. Last name, 4. Phone number, 5. Address, 6. Email 7. Exit");
                
                String userChoice = System.console().readLine();
                switch (userChoice) {
                    case "1":
                        String newId = "";
                        System.out.println("Enter the new ID");
                        newId = idInput(newId);
                        contactsList.get(editIndex).setId(newId);
                        writeToFile();
                        System.out.println("ID updated!");
                        System.out.println("---------------");
                        userConfirm();
                        break;
                    case "2":
                        String newFirst = "";
                        System.out.println("Enter the new first name");
                        newFirst = firstNameInput(newFirst);
                        contactsList.get(editIndex).setFirstName(newFirst);
                        writeToFile();
                        System.out.println("First name updated!");
                        System.out.println("---------------");
                        userConfirm();
                        break;
                    case "3":
                        String newLast = "";
                        System.out.println("Enter the new last name");
                        newLast = lastNameInput(newLast);
                        contactsList.get(editIndex).setLastName(newLast);
                        writeToFile();
                        System.out.println("Last name updated!");
                        System.out.println("---------------");
                        userConfirm();
                        break;
                    case "4":
                        String newPhone = "";
                        System.out.println("Enter the new Phone number");
                        newPhone = phoneNumberInput(newPhone);
                        contactsList.get(editIndex).setPhoneNumber(newPhone);
                        writeToFile();
                        System.out.println("Phone number updated!");
                        System.out.println("---------------");
                        userConfirm();
                        break;
                    case "5":
                        String newAddress = "";
                        System.out.println("Enter the new Address");
                        newAddress = addressInput(newAddress);
                        contactsList.get(editIndex).setAddress(newAddress);
                        writeToFile();
                        System.out.println("Address updated!");
                        System.out.println("---------------");
                        userConfirm();
                        break;
                    case "6":
                        String newEmail = "";
                        System.out.println("Enter the new email");
                        newEmail = emailInput(newEmail);
                        contactsList.get(editIndex).setEmail(newEmail);
                        writeToFile();
                        System.out.println("Email updated!");
                        System.out.println("---------------");
                        userConfirm();
                        break;
                    case "7":
                        exit = true;
                        break;
                    default :
                        System.out.println("Invalid input");
                        break;
                }
            } else {
                System.out.println("The contact you entered did not exist. Press enter to continue");
                String userConf = System.console().readLine();
                exit = true;
            }
        }
    }
    /**
     * idInput is the method for inputting and checking an SSN number for the contact
     * @param idPattern is the regex pattern for the ID
     * @param idMatch is the regex matcher for the ID
     * @param id is the recieved parameter of the ID
     * @return the method returns the set ID back
     */
    public static String idInput(String id) {
        Boolean validInput = false;
        //Getting the validation to take into attention specific months may take time, but the capture group for specific day ranges
        //is way better than just allowing 39 days in a month.
        Pattern idPattern = Pattern.compile("(?:[0-2]{1}[0-9]{1}|[3]{1}[0-1]{1})(?:[0]{1}[0-9]{1}|[1][0-2]{1})[0-9]{2}[\\+|\\-|A]{1}[0-9]{3}[A-Z0-9]{1}");
        while (!validInput) {
            id = System.console().readLine();
            Matcher idMatch = idPattern.matcher(id);
            if (idMatch.matches()) {
                validInput = true;
                return id;
            } else {
                System.out.println("Invalid ID. Give a legitimate Finnish SSN");
            }
        }
        return id;
    }
    /**
     * firstNameInput is the method for inputtting and checking the first name for the contact
     * @param firstNamePattern is the regex pattern for the first name
     * @param firstNameMatch is the regex matcher for the first name
     * @param first is the recieved parameter of the first name
     * @return the method returns the inputted name back
     */
    public static String firstNameInput(String first) {
        Boolean validInput = false;
        Pattern firstNamePattern = Pattern.compile("[A-Z]{1}[a-zA-Z\\- ]{1,11}");
        while (!validInput) {
            first = System.console().readLine();
            Matcher firstNameMatch = firstNamePattern.matcher(first);
            if (firstNameMatch.matches()) {
                validInput = true;
                return first;
            } else {
                System.out.println("Invalid first name. Capitalize the first letter and keep the size between 2-12 letters");
            }
        }
        return first;
    }
    /**
     * lastNameInput is the method for inputting and checking the last name for the contact.
     * @param lastNamePattern is the regex pattern for the last name
     * @param lastNameMatch is the regex matcher for the last name
     * @param last is the reciecved paramter of the last name
     * @return the method returns the inputted surname back
     */
    public static String lastNameInput(String last) {
        Boolean validInput = false;
        Pattern lastNamePattern = Pattern.compile("[A-Z]{1}[a-zA-Z\\- ]{1,20}");
        while (!validInput) {
            last = System.console().readLine();
            Matcher lastNameMatch = lastNamePattern.matcher(last);
            if (lastNameMatch.matches()) {
                validInput = true;
                return last;
            } else {
                System.out.println("Invalid last name. Capitalize the first letter and keep the size between 2-20 letters");
            }
        }
        return last;
    }
    /**
     * phoneNumberInput is the method for inputting and checking the phone number for the contact.
     * @param phonePattern is the regex pattern for the number
     * @param phoneMatch is the regex matcher for the number
     * @param phone is the recieved parameter of the phonenumber
     * @return the method returns the inputted phone number back
     */
    public static String phoneNumberInput(String phone) {
        Boolean validInput = false;
        Pattern phonePattern = Pattern.compile("\\+358(?:40|41|42|43|44|45|46|49|50)[0-9]{7}");
        while (!validInput) {
            phone = System.console().readLine();
            Matcher phoneMatch = phonePattern.matcher(phone);
            if (phoneMatch.matches()) {
                validInput = true;
                return phone;
            } else {
                System.out.println("Invalid phone number, use the +358 format and proper length");
            }
        }
        return phone;
    }
    /**
     * addressInput is the method for inputting and checking the phone number for the contact.
     * @param addressPattern is the regex pattern for the address
     * @param addresssMatch is the regex matcher for the address
     * @param address is the recieved paramter of the address
     * @return the method returns the inputted address
     */
    public static String addressInput(String address) {
        Boolean validInput = false;
        Pattern addressPattern = Pattern.compile("[A-Z]{1}[a-zA-Z0-9 ]{1,40}|");
        while (!validInput) {
            address = System.console().readLine();
            Matcher addressMatch = addressPattern.matcher(address);
            if (addressMatch.matches()) {
                validInput = true;
                return address;
            } else {
                System.out.println("Invalid address. Please give a legitimate one, or leave it empty");
            }
        }
        return address;
    }
    /**
     * addressInput is the method for inputting and checking the phone number for the contact.
     * @param emailPattern is the regex pattern for the email address
     * @param emailMatch is the regex matcher for the email address
     * @param email is the recieved parameter of the email
     * @return the method returns the inputted email address
     */
    public static String emailInput(String email) {
        Boolean validInput = false;
        Pattern emailPattern = Pattern.compile("[\\.a-z0-9]{1,30}+[\\@]{1}[a-z0-9]{2,8}[\\.]{1}[a-z]{1,4}|");
        while (!validInput) {
            email = System.console().readLine();
            Matcher emailMatch = emailPattern.matcher(email);
            if (emailMatch.matches()) {
                validInput = true;
                return email;
            } else {
                System.out.println("Invalid e-mail address. Please give a legitimate one, or leave it empty");
            }
        }
        return email;
    }
}