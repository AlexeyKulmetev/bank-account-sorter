package strategy.input;

import collection.CustomArrayList;
import data.BankAccount;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ConsoleInputStrategy implements DataInputStrategy {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public CustomArrayList<BankAccount> fill(int length) {
        CustomArrayList<BankAccount> accounts = new CustomArrayList<>();


        IntStream.range(0, length)
                .mapToObj(i -> createValidAccountFromConsole(i + 1))
                .forEach(accounts::add);

        return accounts;
    }


    private BankAccount createValidAccountFromConsole(int accountIndex) {
        while (true) {
            System.out.println("\nВвод данных для счета №" + accountIndex);

            System.out.print("Введите номер счета (мин. 5 симв.): ");
            String number = scanner.next();

            System.out.print("Введите имя владельца: ");
            String holder = scanner.next();

            System.out.print("Введите начальный баланс: ");
            double balance = scanner.nextDouble();

            try {

                return new BankAccount.Builder()
                        .setAccountNumber(number)
                        .setAccountHolder(holder)
                        .setBalance(balance)
                        .build();
            } catch (IllegalArgumentException e) {

                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Попробуйте ввести данные для этого счета заново.");
            }
        }
    }
}