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
    //May or may not be used. Will stay here just in case
    public String getId() {
        return id;
    }
    public String getFirstname() {
        return firstName;
    }
    public String getLastname() {
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
}
//This class will work as a constructor of the contact.
