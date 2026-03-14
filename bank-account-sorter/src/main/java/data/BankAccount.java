package data;

public class BankAccount {
    private final String accountNumber;
    private final String accountHolder;
    private final double balance;


    private BankAccount(Builder builder) {
        this.accountNumber = builder.accountNumber;
        this.accountHolder = builder.accountHolder;
        this.balance = builder.balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

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

    public static class Builder {
        private String accountNumber;
        private String accountHolder;
        private double balance;

        public Builder setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder setAccountHolder(String accountHolder) {
            this.accountHolder = accountHolder;
            return this;
        }

        public Builder setBalance(double balance) {
            this.balance = balance;
            return this;
        }

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
