import java.io.*;
import java.util.ArrayList;
//Serializable interface also used here to utilize ObjectOutuputStream and to not cause IOExceptions
/**
 * Contact class contains the object which will be saved to the file
 * 
 * It implements the Serializable interface for use with the ObjectStreams and arraylists.
 */
public class Contact implements Serializable { //Class renamed to reflect it more clearly
                                        //Info class name didn't make much sense and was confusing
    public String id;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String address;
    public String email;
    /**
     * Contact constructor uses the public values defined in the Contact class
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
     * @return getId returns the contacts' ID
     */
    public String getId() {
        return id;
    }
    /**
     * @return getFirstName returns the contacts' first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @return getFirstName returns the contacts' last name
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @return getPhoneNumber returns the contacts' phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * 
     * @return getaddress returns the contacts' address
     */
    public String getAddress() {
        return address;
    }
    /**
     * @return getEmail returns the contacts' email address
     */
    public String getEmail() {
        return email;
    }
    /**
     * toString formats the contact information to the arrayList
     */
    public String toString() {
        //Will return the object values separated by commas
        return id + ", " + firstName + ", " + lastName + ", " + phoneNumber + ", " + address + ", " + email;
    }
    /**
     * setID method sets a new Id for the contact
     * @param newId is recieved from the editing method in ContactsApp class
     */
    //Setter methods for the editing functions
    public void setId(String newId) {
        this.id = newId;
    }
    /**
     * setFirstName method sets a new first name for the contact
     * @param newFirstName is recieved from the editing method in ContactsApp class
     */
    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }
    /**
     * setLastName method sets a new last name for the contact
     * @param newLastName is recieved from the editing method in ContactsApp class
     */
    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }
    /**
     * setPhoneNumber method sets a new phone number for the contact
     * @param newPhoneNumber is recieved from the editing method in ContactsApp class
     */
    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }
    /**
     * setAddresss method sets a new address for the contact
     * @param newAddress is recieved from the editing method in ContactsApp class
     */
    public void setAddress(String newAddress) {
        this.address = newAddress;
    }
    /**
     * setEmail method sets a new email address for the contact
     * @param newEmail is recieved from the editing method in ContactsApp class
     */
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }
}
//This class will work as a constructor of the contact.
