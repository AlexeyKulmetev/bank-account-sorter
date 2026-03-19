package strategy.input;

import collection.CustomArrayList;
import data.BankAccount;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomInputStrategy implements DataInputStrategy {
    private final Random random = new Random();

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