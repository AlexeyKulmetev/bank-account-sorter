package strategy.input;

import collection.CustomArrayList;
import data.BankAccount;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Стратегия случайной генерации банковских счетов.
 * Создает заданное количество счетов со случайными данными.
 *
 * @author Марат Морозов
 */
public class RandomInputStrategy implements DataInputStrategy {
    private final Random random = new Random();

    /**
     * Заполняет коллекцию случайно сгенерированными банковскими счетами.
     * Номера счетов: ACC-1000 до ACC-9999
     * Имена владельцев: Client_1, Client_2, ...
     * Балансы: от 100 до 5100
     *
     * @param length количество счетов для генерации
     * @return коллекция случайно сгенерированных счетов
     */
    @Override
    public CustomArrayList<BankAccount> fill(int length) {
        CustomArrayList<BankAccount> accounts = new CustomArrayList<>();


        IntStream.range(0, length)
                .mapToObj(i -> {
                    String number = "ACC-" + (1000 + random.nextInt(9000));
                    String holder = "Client_" + (i + 1);
                    double balance = 100 + (random.nextDouble() * 5000);

                    return new BankAccount.Builder()
                            .setAccountNumber(number)
                            .setAccountHolder(holder)
                            .setBalance(balance)
                            .build();
                })
                .forEach(accounts::add);

        return accounts;
    }
}