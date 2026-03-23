package strategy.sort;

import collection.CustomArrayList;
import data.BankAccount;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class CustomSortUtilsTest {
    // Вспомогательный метод
    private BankAccount acc(String number, String holder, double balance) {
        return new BankAccount.Builder()
                .setAccountNumber(number)
                .setAccountHolder(holder)
                .setBalance(balance)
                .build();
    }

    @Test
    void bubbleSort_testNull() {
        assertDoesNotThrow(() -> {
            CustomSortUtils.bubbleSort(null, Comparator.comparing(BankAccount::getAccountHolder));
        });
    }

    @Test
    void bubbleSort_testEmpty() {
        assertDoesNotThrow(() -> {
            CustomSortUtils.bubbleSort(new CustomArrayList<BankAccount>(), Comparator.comparing(BankAccount::getAccountHolder));
        });
    }

    @Test
    void bubbleSort_testSingle() {
        CustomArrayList<BankAccount> accounts = new CustomArrayList<BankAccount>();
        accounts.add(acc("654321", "Петров Петр", 2000.75));
        assertDoesNotThrow(() -> {
            CustomSortUtils.bubbleSort(accounts, Comparator.comparing(BankAccount::getAccountHolder));
        });
        assertEquals(1, accounts.size());
        BankAccount first = accounts.get(0);
        assertEquals("654321", first.getAccountNumber());
        assertEquals("Петров Петр", first.getAccountHolder());
        assertEquals(2000.75, first.getBalance());
    }

    @Test
    void bubbleSort_testMoreThanOne() {
        CustomArrayList<BankAccount> accounts = new CustomArrayList<>();

        accounts.add(acc("55555", "Иван", 1000.50));
        accounts.add(acc("22222", "Петр", 2000.75));
        accounts.add(acc("11111", "Анна", 0.0));
        accounts.add(acc("44444", "LeBron James", 1000000000.01));
        accounts.add(acc("33333", "James Bond", 10748013));

        CustomSortUtils.bubbleSort(accounts, Comparator.comparing(BankAccount::getAccountNumber));

        assertEquals(5, accounts.size());
        assertEquals("11111", accounts.get(0).getAccountNumber());
        assertEquals("22222", accounts.get(1).getAccountNumber());
        assertEquals("33333", accounts.get(2).getAccountNumber());
        assertEquals("44444", accounts.get(3).getAccountNumber());
        assertEquals("55555", accounts.get(4).getAccountNumber());
    }


    @Test
    void testSortEvenBalances_onlyEvenSorted() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        list.add(acc("11111", "A", 5));
        list.add(acc("22222", "B", 8));
        list.add(acc("33333", "C", 3));
        list.add(acc("44444", "D", 2));

        CustomSortUtils.sortEvenBalances(list);

        assertEquals(5, list.get(0).getBalance());
        assertEquals(3, list.get(2).getBalance());

        assertEquals(2, list.get(1).getBalance());
        assertEquals(8, list.get(3).getBalance());
    }

    @Test
    void testSortEvenBalances_allEven() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        list.add(acc("11111", "A", 10));
        list.add(acc("22222", "B", 4));
        list.add(acc("33333", "C", 6));

        CustomSortUtils.sortEvenBalances(list);

        assertEquals(4, list.get(0).getBalance());
        assertEquals(6, list.get(1).getBalance());
        assertEquals(10, list.get(2).getBalance());
    }

    @Test
    void testSortEvenBalances_noEvenNumbers() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        list.add(acc("11111", "A", 5));
        list.add(acc("22222", "B", 3));
        list.add(acc("33333", "C", 7));

        CustomSortUtils.sortEvenBalances(list);

        assertEquals(5, list.get(0).getBalance());
        assertEquals(3, list.get(1).getBalance());
        assertEquals(7, list.get(2).getBalance());
    }

    @Test
    void testSortEvenBalances_mixedComplex() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        list.add(acc("11111", "A", 7));   // odd
        list.add(acc("22222", "B", 12));  // even
        list.add(acc("33333", "C", 5));   // odd
        list.add(acc("44444", "D", 4));   // even
        list.add(acc("55555", "E", 9));   // odd
        list.add(acc("66666", "F", 2));   // even

        CustomSortUtils.sortEvenBalances(list);

        assertEquals(7, list.get(0).getBalance());
        assertEquals(5, list.get(2).getBalance());
        assertEquals(9, list.get(4).getBalance());

        assertEquals(2, list.get(1).getBalance());
        assertEquals(4, list.get(3).getBalance());
        assertEquals(12, list.get(5).getBalance());
    }

    @Test
    void testSortEvenBalances_emptyList() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        assertDoesNotThrow(() -> CustomSortUtils.sortEvenBalances(list));
        assertEquals(0, list.size());
    }

    @Test
    void testSortEvenBalances_singleElement() {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        list.add(acc("11111", "A", 2));

        assertDoesNotThrow(() -> CustomSortUtils.sortEvenBalances(list));
        assertEquals(2, list.get(0).getBalance());
    }
}
