package validation;

public class Validator {


    public static boolean isValidAccountNumber(String number) {
        return number != null && number.trim().length() >= 5;
    }


    public static boolean isValidHolderName(String name) {
        return name != null && !name.trim().isEmpty();
    }


    public static boolean isValidBalance(double balance) {
        return balance >= 0;
    }
}