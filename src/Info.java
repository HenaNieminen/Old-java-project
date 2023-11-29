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
            FileOutputStream fos = new FileOutputStream(new File("contacts.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readContact() {
        try {
            FileInputStream fis = new FileInputStream(new File("contacts.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            newContact = (Info) ois.readObject();
            System.out.println(newContact.toString());
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//This is the class which contains the user given information and stores them to a file.

//Regex will be used to validate user input, but takes a while to figure out how to make a working pattern