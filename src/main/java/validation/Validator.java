package validation;

import data.BankAccount;

public class Validator {
    private Validator() {
        /* This utility class should not be instantiated */
    }


    public static boolean isValidAccountNumber(String number) {
        return number != null && number.trim().length() >= 5;
    }


    public static boolean isValidHolderName(String name) {
        return name != null && !name.trim().isEmpty();
    }


    public static boolean isValidBalance(double balance) {
        return balance >= 0;
    }

    public static boolean isValidAccount(BankAccount account) {
        return isValidAccountNumber(account.getAccountNumber())
                && isValidHolderName(account.getAccountHolder())
                && isValidBalance(account.getBalance());
    }
}