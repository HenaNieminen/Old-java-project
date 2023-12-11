import java.io.*;
import java.util.ArrayList;
//Serializable interface also used here to utilize ObjectOutuputStream and to not cause IOExceptions
class Contact implements Serializable { //Class renamed to reflect it more clearly
                                        //Info class name didn't make much sense and was confusing
    public String id;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String address;
    public String email;

    public Contact(String id, String firstName, String lastName, 
    String phoneNumber, String address, String email) {
        //Constructor works by initializing values given to it by the addContact method
        //in contactsApp class.
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
    //Getter methods
    //Will be used for the search and edit functions
    public String getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public String toString() {
        //Will return the object values separated by commas
        return id + ", " + firstName + ", " + lastName + ", " + phoneNumber + ", " + address + ", " + email;
    }
    //Setter methods for the editing functions
    public void setId(String newId) {
        this.id = newId;
    }
    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }
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
