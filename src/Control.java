import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Control {
    public static boolean validId(String id) {
        String regex = "[0-3]{1}[1-9]{1}[0-9]{2}+[-A][0-9]{3}[A-Z]{1}";
        Pattern valid = Pattern.compile(regex);
        Matcher idmatch =  valid.matcher(id);
        return idmatch.matches();
    }
    public static boolean validFirstName(String firstName) {
        String regex = "[A-Z]{1}[a-z]{12}";
        Pattern valid = Pattern.compile(regex);
        Matcher firstMatch = valid.matcher(firstName);
        return firstMatch.matches();
    }
}

// Decided to switch regex and validation to a separate Control class. Just to keep things more clearer
// May get changed and consolidated again