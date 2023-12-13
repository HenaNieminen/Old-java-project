import java.io.*;
import java.util.ArrayList;
//Serializable interface also used here to utilize ObjectOutuputStream and to not cause IOExceptions
/**
 * Class Contact
 * 
 * This class contains the main constructor for the object used in the app and will build the contact
 * information to the file.
 * 
 * It implements the Serializable interface for use with the ObjectStreams and arraylists.
 */
final class Contact implements Serializable { //Class renamed to reflect it more clearly
                                        //Info class name didn't make much sense and was confusing
    public String id;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String address;
    public String email;
    /**
     * Contact constructor
     * @param id used as variable for the Finnish SSN
     * @param firstName contacts' first name
     * @param lastName contacts' last name
     * @param phoneNumber contacts' phone number
     * @param address contacts' home address
     * @param email contacts' email address
     * 
     */
    public Contact(String id, String firstName, String lastName, 
    String phoneNumber, String address, String email) {
        //Constructor works by initializing values given to it by the addContact method
        //in contactsApp class.
        /**
         * The constructor will only initialize the values given to it by referring as
         * this value. The setter methods further down this class work for the editing method
         * in the ContactsApp class.
         */
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
    //Getter methods
    //Will be used for the search and edit functions
    /**
     * getId returns the contacts' ID
     */
    public String getId() {
        return id;
    }
    /**
     * getFirstName returns the contacts' first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * getLastName returns the contacts' last name
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * getPhoneNumber returns the contacts' phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * getAddress returns the contacts' address
     */
    public String getAddress() {
        return address;
    }
    /**
     * getPhoneNumber returns the contacts' email address
     */
    public String getEmail() {
        return email;
    }
    /**
     * toString returns the contacts information into a comma separated format
     * (I havent used this at all. May not be in the final code)
     */
    public String toString() {
        //Will return the object values separated by commas
        return id + ", " + firstName + ", " + lastName + ", " + phoneNumber + ", " + address + ", " + email;
    }
    /**
     * setID method
     * @param newId is recieved from the editing method in ContactsApp class
     */
    //Setter methods for the editing functions
    public void setId(String newId) {
        this.id = newId;
    }
    /**
     * setFirstName method
     * @param newFirstName is recieved from the editing method in ContactsApp class
     */
    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }
    /**
     * setLastName method
     * @param newLastName is recieved from the editing method in ContactsApp class
     */
    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }
    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }
    public void setAddress(String newAddress) {
        this.address = newAddress;
    }
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }
}
//This class will work as a constructor of the contact.
