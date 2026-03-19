package strategy.input;

import collection.CustomArrayList;
import data.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomInputStrategy implements DataInputStrategy {
    private final Random random = new Random();

    @Override
    public CustomArrayList<BankAccount> fill(int length) {
        CustomArrayList<BankAccount> accounts = new CustomArrayList<>();
        for (int i = 0; i < length; i++) {

            String number = "ACC-" + (1000 + random.nextInt(9000));
            String holder = "Client_" + (i + 1);
            double balance = 100 + (random.nextDouble() * 5000);


            accounts.add(new BankAccount.Builder()
                    .setAccountNumber(number)
                    .setAccountHolder(holder)
                    .setBalance(balance)
                    .build());
        }
        return accounts;
    }
}