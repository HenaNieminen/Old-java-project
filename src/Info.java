import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;

class Info implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;

    public Info(String id, String firstName, String lastName, 
    String phone, String address, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public String toString() {
        return id + "," + firstName + "," + lastName + "," + phone + ", " + "," + address + "," + email;
    }
    public void addContact() {
        File contacts = new File("Contacts.txt");
        System.out.println("Give the ID of the contact");
        this.id = System.console().readLine();
        System.out.println("Give the first name");
        this.firstName = System.console().readLine();
        System.out.println("Give the last name");
        this.lastName = System.console().readLine();
        System.out.println("(Opt.)Give the address");
        this.address = System.console().readLine();
        System.out.println("(Opt.) Give the email");
        this.email = System.console().readLine();
        System.out.println("Contact added!");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(contacts));
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean validId(String id) {
        String regex = "[0-3]{1}[1-9]{1}[0-9]{2}+[-A][0-9]{3}[A-Z]{1}";
        Pattern valid = Pattern.compile(regex);
        Matcher idmatch =  valid.matcher(id);
        return idmatch.matches();
    }
    public static boolean validFirstName(String firstName) {
        String regex = "[A-Z]{20}"
        Pattern valid = Pattern.compile(regex);
        Matcher firstMatch = valid.matcher(firstName);
        return firstMatch.matches();
    }
    public static boolean validLastName(String lastName) {
        String regex =;
    }
}

//This is the class which contains the user given information and stores them to a file.

//Regex will be used to validate user input, but takes a while to figure out how to make a working pattern