package strategy;

import collection.CustomArrayList;
import data.BankAccount;

import java.util.concurrent.atomic.AtomicInteger;

public class MultithreadedSearchService {


    public static int countByBalance(CustomArrayList<BankAccount> accounts, double targetBalance) {
        if (accounts == null || accounts.isEmpty()) return 0;

        int size = accounts.size();
        AtomicInteger count = new AtomicInteger(0);


        int mid = size / 2;

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < mid; i++) {
                if (Math.abs(accounts.get(i).getBalance() - targetBalance) < 0.001) {
                    count.incrementAndGet();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = mid; i < size; i++) {
                if (Math.abs(accounts.get(i).getBalance() - targetBalance) < 0.001) {
                    count.incrementAndGet();
                }
            }
        });

        try {
            thread1.start();
            thread2.start();


            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.err.println("Ошибка выполнения потоков: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        return count.get();
    }
}