package data;

import java.util.Objects;

/**
 * Представляет банковский счет с номером, владельцем и балансом.
 * Класс неизменяемый (immutable), использует паттерн Builder для создания объектов.
 *
 * @author Ася Айдаралиева
 */
public class BankAccount {
    private final String accountNumber;
    private final String accountHolder;
    private final double balance;


    private BankAccount(Builder builder) {
        this.accountNumber = builder.accountNumber;
        this.accountHolder = builder.accountHolder;
        this.balance = builder.balance;
    }

    /**
     * Возвращает номер счета.
     *
     * @return номер счета (минимальная длина 5 символов)
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Возвращает имя владельца счета.
     *
     * @return имя владельца счета (не может быть пустым)
     */
    public String getAccountHolder() {
        return accountHolder;
    }

    /**
     * Возвращает баланс счета.
     *
     * @return баланс счета (не может быть отрицательным)
     */
    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "number='" + accountNumber + '\'' +
                ", holder='" + accountHolder + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Double.compare(that.balance, balance) == 0 &&
                Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(accountHolder, that.accountHolder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, accountHolder, balance);
    }

    /**
     * Билдер для создания объектов BankAccount.
     * Предоставляет текучий интерфейс для установки полей с валидацией.
     */
    public static class Builder {
        private String accountNumber;
        private String accountHolder;
        private double balance;

        /**
         * Устанавливает номер счета.
         *
         * @param accountNumber номер счета (должен содержать минимум 5 символов)
         * @return этот же экземпляр строителя для цепочки вызовов
         */
        public Builder setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        /**
         * Устанавливает имя владельца счета.
         *
         * @param accountHolder имя владельца (не может быть пустым)
         * @return этот же экземпляр строителя для цепочки вызовов
         */
        public Builder setAccountHolder(String accountHolder) {
            this.accountHolder = accountHolder;
            return this;
        }

        /**
         * Устанавливает баланс счета.
         *
         * @param balance баланс счета (не может быть отрицательным)
         * @return этот же экземпляр строителя для цепочки вызовов
         */
        public Builder setBalance(double balance) {
            this.balance = balance;
            return this;
        }

        /**
         * Создает объект BankAccount после валидации всех полей.
         *
         * @return новый экземпляр BankAccount
         * @throws IllegalArgumentException если какое-либо поле не прошло валидацию
         */
        public BankAccount build() {
            if (!validation.Validator.isValidAccountNumber(this.accountNumber)) {
                throw new IllegalArgumentException("Ошибка: Номер счета должен быть не менее 5 символов.");
            }


            if (!validation.Validator.isValidHolderName(this.accountHolder)) {
                throw new IllegalArgumentException("Ошибка: Имя владельца не может быть пустым.");
            }


            if (!validation.Validator.isValidBalance(this.balance)) {
                throw new IllegalArgumentException("Ошибка: Баланс не может быть отрицательным.");
            }


            return new BankAccount(this);
        }
    }
}