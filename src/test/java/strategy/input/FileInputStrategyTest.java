package strategy.input;

import collection.CustomArrayList;
import data.BankAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileInputStrategyTest {

    @Test
    @DisplayName("Все корректные строки должны быть корректно преобразованы в аккаунты")
    void shouldCreateAccountsForEachLineInAllValidFile() {
        Path filePath = Path.of("src", "test", "resources", "valid_accounts.txt");

        String some = filePath + "\n";
        InputStream in = new ByteArrayInputStream(some.getBytes());
        System.setIn(in);
        DataInputStrategy strategy = new FileInputStrategy();
        CustomArrayList<BankAccount> accounts;
        try {
            accounts = strategy.fill(5);
        } finally {
            System.setIn(System.in);
        }

        assertNotNull(accounts);
        assertEquals(5, accounts.size());

        CustomArrayList<BankAccount> correctAccounts = new CustomArrayList<>();

        correctAccounts.add(new BankAccount.Builder()
                .setAccountNumber("123456")
                .setAccountHolder("Иванов Иван")
                .setBalance(1000.50)
                .build());

        correctAccounts.add(new BankAccount.Builder()
                .setAccountNumber("654321")
                .setAccountHolder("Петров Петр")
                .setBalance(2000.75)
                .build());

        correctAccounts.add(new BankAccount.Builder()
                .setAccountNumber("987654")
                .setAccountHolder("Сидорова Анна")
                .setBalance(0.0)
                .build());

        correctAccounts.add(new BankAccount.Builder()
                .setAccountNumber("2362341")
                .setAccountHolder("LeBron James")
                .setBalance(1000000000.01)
                .build());

        correctAccounts.add(new BankAccount.Builder()
                .setAccountNumber("ACC-007")
                .setAccountHolder("James Bond")
                .setBalance(10748013)
                .build());

        System.out.println(correctAccounts);
        for (int i = 0; i < 5; i++) {
            assertEquals(correctAccounts.get(i), accounts.get(i));
        }
    }

    @Test
    @DisplayName("Все некорректные строки должны быть пропущены")
    void shouldSkipInvalidLines() {
        Path filePath = Path.of("src", "test", "resources", "invalid_accounts.txt");

        String some = filePath + "\n";
        InputStream in = new ByteArrayInputStream(some.getBytes());
        System.setIn(in);
        DataInputStrategy strategy = new FileInputStrategy();
        CustomArrayList<BankAccount> accounts;
        try {
            accounts = strategy.fill(5);
        } finally {
            System.setIn(System.in);
        }

        assertNotNull(accounts);
        assertTrue(accounts.isEmpty());
    }

    @Test
    @DisplayName("Считывается не больше строк, чем есть в файле")
    void shouldNotCreateMoreAccountsThanLinesInAFile() {
        Path filePath = Path.of("src", "test", "resources", "valid_accounts.txt");

        String some = filePath + "\n";
        InputStream in = new ByteArrayInputStream(some.getBytes());
        System.setIn(in);
        DataInputStrategy strategy = new FileInputStrategy();
        CustomArrayList<BankAccount> accounts;
        try {
            accounts = strategy.fill(10);
        } finally {
            System.setIn(System.in);
        }

        assertNotNull(accounts);
        assertEquals(5, accounts.size());

        CustomArrayList<BankAccount> correctAccounts = new CustomArrayList<>();

        correctAccounts.add(new BankAccount.Builder()
                .setAccountNumber("123456")
                .setAccountHolder("Иванов Иван")
                .setBalance(1000.50)
                .build());

        correctAccounts.add(new BankAccount.Builder()
                .setAccountNumber("654321")
                .setAccountHolder("Петров Петр")
                .setBalance(2000.75)
                .build());

        correctAccounts.add(new BankAccount.Builder()
                .setAccountNumber("987654")
                .setAccountHolder("Сидорова Анна")
                .setBalance(0.0)
                .build());

        correctAccounts.add(new BankAccount.Builder()
                .setAccountNumber("2362341")
                .setAccountHolder("LeBron James")
                .setBalance(1000000000.01)
                .build());

        correctAccounts.add(new BankAccount.Builder()
                .setAccountNumber("ACC-007")
                .setAccountHolder("James Bond")
                .setBalance(10748013)
                .build());

        System.out.println(correctAccounts);
        for (int i = 0; i < 5; i++) {
            assertEquals(correctAccounts.get(i), accounts.get(i));
        }
    }

    @Test
    @DisplayName("Если валидные и невалидные строки вперемешку, считаются только валидные")
    void shouldReadOnlyValidLines() {
        Path filePath = Path.of("src", "test", "resources", "semi_valid_accounts.txt");
        String some = filePath + "\n";

        InputStream in = new ByteArrayInputStream(some.getBytes());
        System.setIn(in);
        DataInputStrategy strategy = new FileInputStrategy();
        CustomArrayList<BankAccount> accounts;
        try {
            accounts = strategy.fill(10);
        } finally {
            System.setIn(System.in);
        }

        assertNotNull(accounts);
        assertEquals(2, accounts.size());

        CustomArrayList<BankAccount> correctAccounts = new CustomArrayList<>();

        correctAccounts.add(new BankAccount.Builder()
                .setAccountNumber("2362341")
                .setAccountHolder("LeBron James")
                .setBalance(1000000000.01)
                .build());

        correctAccounts.add(new BankAccount.Builder()
                .setAccountNumber("654321")
                .setAccountHolder("Петров Петр")
                .setBalance(2000.75)
                .build());


        System.out.println(correctAccounts);
        for (int i = 0; i < 2; i++) {
            assertEquals(correctAccounts.get(i), accounts.get(i));
        }
    }
}