package util;

import java.util.regex.Pattern;

public class Validator {

    private static final String nameRegex = "^[A-Za-zÀ-ÖØ-öø-ÿ\\\\s]+$";
    private static final String emailRegex = "^[\\\\w-\\\\.]+@([\\\\w-]+\\\\.)+[\\\\w-]{2,4}$"; 

    public static boolean isValidName (String name) {
        return name != null && Pattern.matches(nameRegex, name) && name.trim().length() > 2;
    }

    public static boolean isValidEmail(String email) {
        return email != null && Pattern.matches(emailRegex, email);
    }

    


}
