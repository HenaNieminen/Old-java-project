import java.io.*;
import java.util.ArrayList;

/**
 * Contact class contains the object which will be saved to the file
 * @author Henri Nieminen
 * It implements the Serializable interface for use with the ObjectStreams and arraylists.
 * @param id works as the SSN of the object
 * @param firstName works as the first name of the object
 * @param lastName works as the last name of the object
 * @param address works as the address of the object
 * @param email works as the email address of the object
 */
public final class Contact implements Serializable {
                                                
    public String id;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String address;
    public String email;
    /**
     * Contact constructor uses the public values defined in the Contact class
     * @param id inserted as the objects ID
     * @param firstName inserted as the objects first name
     * @param lastName inserted as the objects last name
     * @param phoneNumber inserted as the objects phone number
     * @param address inserted as the objects address
     * @param email inserted as the objects e-mail address
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
