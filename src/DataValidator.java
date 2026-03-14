public class DataValidator {
    public static void validate(String accountNumber, String accountHolder, double balance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Номер счёта не может быть пустым");
        }
        // Простейшая проверка: номер должен содержать только цифры (можно расширить)
        if (!accountNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Номер счёта должен содержать только цифры");
        }
        if (accountHolder == null || accountHolder.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя владельца не может быть пустым");
        }
        // Баланс может быть любым числом, но проверим, что это не NaN или бесконечность
        if (Double.isNaN(balance) || Double.isInfinite(balance)) {
            throw new IllegalArgumentException("Некорректное значение баланса");
        }
    }
}