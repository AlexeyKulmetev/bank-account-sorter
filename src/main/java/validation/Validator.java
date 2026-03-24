package validation;

/**
 * Утилитарный класс для валидации данных банковского счета.
 * Содержит статические методы для проверки корректности полей.
 *
 * @author Ася Айдаралиева
 */
public class Validator {

    /**
     * Проверяет корректность номера счета.
     * Номер счета должен быть не null и содержать не менее 5 символов.
     *
     * @param number номер счета для проверки
     * @return true если номер счета корректен, иначе false
     */
    public static boolean isValidAccountNumber(String number) {
        return number != null && number.trim().length() >= 5;
    }

    /**
     * Проверяет корректность имени владельца.
     * Имя не должно быть null или пустой строкой.
     *
     * @param name имя владельца для проверки
     * @return true если имя корректно, иначе false
     */
    public static boolean isValidHolderName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Проверяет корректность баланса.
     * Баланс не может быть отрицательным.
     *
     * @param balance баланс для проверки
     * @return true если баланс корректен (≥ 0), иначе false
     */
    public static boolean isValidBalance(double balance) {
        return balance >= 0;
    }
}