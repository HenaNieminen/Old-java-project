import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Info {
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
    public static void addContact() {
        System.out.println("(Optional) Give the ID of the contact");
        String id = System.console().readLine();
        System.out.println("Give the first name");
        String firstName = System.console().readLine();
        System.out.println("Give the last name");
        String lastName = System.console().readLine();
        System.out.println("(Opt.)Give the address");
        String address = System.console().readLine();
        System.out.println("(Opt.) Give the email");
        String email = System.console().readLine();
    }
}

//This is the class which contains the user given information and stores them to a file.

//Regex will be used to validate user input, but takes a while to figure out how to make a working pattern