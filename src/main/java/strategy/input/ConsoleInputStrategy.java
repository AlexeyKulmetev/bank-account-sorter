package strategy.input;

import collection.CustomArrayList;
import data.BankAccount;
import validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputStrategy implements DataInputStrategy {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public CustomArrayList<BankAccount> fill(int length) {
        CustomArrayList<BankAccount> accounts = new CustomArrayList<>();

        for (int i = 0; i < length; i++) {
            System.out.println("\nВвод данных для счета №" + (i + 1));

            System.out.print("Введите номер счета (мин. 5 симв.): ");
            String number = scanner.next();

            System.out.print("Введите имя владельца: ");
            String holder = scanner.next();

            System.out.print("Введите начальный баланс: ");
            double balance = scanner.nextDouble();

            try {

                BankAccount account = new BankAccount.Builder()
                        .setAccountNumber(number)
                        .setAccountHolder(holder)
                        .setBalance(balance)
                        .build();

                accounts.add(account);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Попробуйте ввести данные для этого счета заново.");
                i--;
            }
        }
        return accounts;
    }
}
