package strategy;

import searchservice.MultithreadedSearchService;
import collection.CustomArrayList;
import data.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultithreadedSearchServiceTest {

    private BankAccount acc(String number, String holder, double balance) {
        return new BankAccount.Builder()
                .setAccountNumber(number)
                .setAccountHolder(holder)
                .setBalance(balance)
                .build();
    }

    @Test
    void testCountByBalance_basicMatch() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        list.add(acc("11111", "A", 100));
        list.add(acc("22222", "B", 200));
        list.add(acc("33333", "C", 100));

        int result = MultithreadedSearchService.countByBalance(list, 100);

        assertEquals(2, result);
    }

    @Test
    void testCountByBalance_noMatches() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        list.add(acc("11111", "A", 10));
        list.add(acc("22222", "B", 20));

        int result = MultithreadedSearchService.countByBalance(list, 100);

        assertEquals(0, result);
    }

    @Test
    void testCountByBalance_emptyList() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        int result = MultithreadedSearchService.countByBalance(list, 100);

        assertEquals(0, result);
    }

    @Test
    void testCountByBalance_nullList() {
        int result = MultithreadedSearchService.countByBalance(null, 100);

        assertEquals(0, result);
    }

    @Test
    void testCountByBalance_singleElement_match() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        list.add(acc("11111", "A", 50));

        int result = MultithreadedSearchService.countByBalance(list, 50);

        assertEquals(1, result);
    }

    @Test
    void testCountByBalance_singleElement_noMatch() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        list.add(acc("11111", "A", 50));

        int result = MultithreadedSearchService.countByBalance(list, 100);

        assertEquals(0, result);
    }

    @Test
    void testCountByBalance_acrossThreads() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        list.add(acc("11111", "A", 100));
        list.add(acc("22222", "B", 1));
        list.add(acc("33333", "C", 2));
        list.add(acc("44444", "D", 100));

        int result = MultithreadedSearchService.countByBalance(list, 100);

        assertEquals(2, result);
    }

    @Test
    void testCountByBalance_withTolerance() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        list.add(acc("11111", "A", 100.0005));
        list.add(acc("22222", "B", 99.9995));
        list.add(acc("33333", "C", 100.01));

        int result = MultithreadedSearchService.countByBalance(list, 100);

        assertEquals(2, result);
    }

    @Test
    void testCountByBalance_largeDataset() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        int expected = 0;

        for (int i = 0; i < 1000; i++) {
            double balance = (i % 10 == 0) ? 500 : i;
            if (balance == 500) expected++;
            list.add(acc("ACC-" + i, "User" + i, balance));
        }

        int result = MultithreadedSearchService.countByBalance(list, 500);

        assertEquals(expected, result);
    }
}