package strategy.input;

import collection.CustomArrayList;
import data.BankAccount;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Стратегия ручного ввода банковских счетов с консоли.
 * Позволяет пользователю вводить данные для каждого счета.
 * При ошибках валидации запрашивает повторный ввод.
 *
 * @author Марат Морозов
 */
public class ConsoleInputStrategy implements DataInputStrategy {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Конструктор. Настраивает Scanner для корректной работы с nextLine().
     */
    public ConsoleInputStrategy() {
        scanner.useDelimiter("\n");
    }

    /**
     * Заполняет коллекцию счетами, введенными пользователем с консоли.
     * Для каждого счета запрашивает номер, имя владельца и баланс.
     * При ошибках валидации повторяет запрос для текущего счета.
     *
     * @param length количество счетов для ввода
     * @return коллекция введенных пользователем счетов
     */
    @Override
    public CustomArrayList<BankAccount> fill(int length) {
        CustomArrayList<BankAccount> accounts = new CustomArrayList<>();


        IntStream.range(0, length)
                .mapToObj(i -> createValidAccountFromConsole(i + 1))
                .forEach(accounts::add);

        return accounts;
    }

    /**
     * Создает один банковский счет на основе ввода пользователя.
     * Повторяет запрос, пока не будут введены корректные данные.
     *
     * @param accountIndex порядковый номер счета (для отображения)
     * @return корректно созданный банковский счет
     */
    private BankAccount createValidAccountFromConsole(int accountIndex) {
        while (true) {
            System.out.println("\nВвод данных для счета №" + accountIndex);

            System.out.print("Введите номер счета (мин. 5 симв.): ");
            String number = scanner.nextLine();

            System.out.print("Введите имя владельца: ");
            String holder = scanner.nextLine();

            System.out.print("Введите начальный баланс: ");
            double balance;
            try {
                balance = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректное число для баланса.");
                continue;
            }

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