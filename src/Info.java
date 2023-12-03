import java.io.*;
import java.util.ArrayList;

class Info implements Serializable {
    //The ArrayList was moved here as a private static variable to be used an initialized here
    //It will also be saved using objectinput and outputstream and there will be a loader method for it

    //Still trying to get the objectoutput and inputstream to work properly. If I cant get everything to work
    // I might consider stuffing everything under one class and just writing strings to the file.
    // Or making a contactApp object
    private static ArrayList<Info> contactInfo = new ArrayList<>();
    private File contact = new File("contacts.txt");
    readFile();
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;


    public Info(String id, String firstName, String lastName, 
    String phone, String address, String email) {
        //Constructor works by initializing the given values from the addcontact method
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    //Getter methods
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
        //Will return the object values into a comma separated format
        return id + "," + firstName + "," + lastName + "," + phone + ", " + "," + address + "," + email;
    }

    public void addContact() {
        //The method for adding contacts
        //Regex will be used to validate input from the user.
        System.out.println("Give the ID of the contact");
        this.id = System.console().readLine();
        System.out.println("Give the first name");
        this.firstName = System.console().readLine();
        System.out.println("Give the last name");
        this.lastName = System.console().readLine();
        System.out.println("Give the phone number");
        this.phone = System.console().readLine();
        System.out.println("(Opt.)Give the address");
        this.address = System.console().readLine();
        System.out.println("(Opt.) Give the email");
        this.email = System.console().readLine();
        System.out.println("Contact added!");
        contactInfo.add(new Info(id, firstName, lastName, phone,address,email));
        try{
            oos = new ObjectOutputStream(new FileOutputStream(contact));
            oos.writeObject(contactInfo);
            oos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readContact() {
        System.out.println(contactInfo);
    }
    public void readFile() {
        // Reads the file via objectinput stream
        if(contact.isFile()){
            ois = new ObjectInputStream(new FileInputStream(contact));
            contactInfo = (ArrayList<Info>)ois.readObject();
            ois.close();
        }
    }
}
//This is the class which contains the user given information and stores them to a file.
