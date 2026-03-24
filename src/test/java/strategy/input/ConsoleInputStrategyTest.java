package strategy.input;

import collection.CustomArrayList;
import data.BankAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputStrategyTest {

    @Test
    @DisplayName("Корректный ввод 1-го аккаунта возвращает список длиной 1 с корректным объектом")
    void shouldCreateOneValidAccountFromConsole() {
        String correctAccountNumber = "123456";
        String correctHolderName = "Vasya Pupkine";
        Double correctBalance = 18303.78;

        String input = correctAccountNumber + "\n" + correctHolderName + "\n" + correctBalance + "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        DataInputStrategy strategy = new ConsoleInputStrategy();
        CustomArrayList<BankAccount> accounts;
        try {
            accounts = strategy.fill(1);
        } finally {
            System.setIn(System.in);
        }

        assertEquals(1, accounts.size());
        BankAccount retrievedAccount = accounts.get(0);
        assertEquals(correctAccountNumber, retrievedAccount.getAccountNumber());
        assertEquals(correctHolderName, retrievedAccount.getAccountHolder());
        assertEquals(correctBalance, retrievedAccount.getBalance());
    }

    @Test
    @DisplayName("Некорректный номер приводит к повторному вводу")
    void shouldAskForCorrectAccountNumber() {
        String incorrectAccountNumber = ".";
        String correctAccountNumber = "123456";
        String correctHolderName = "Vasya Pupkine";
        Double correctBalance = 18303.78;

        String input = incorrectAccountNumber + "\n" + correctHolderName + "\n" + correctBalance + "\n" + correctAccountNumber + "\n" + correctHolderName + "\n" + correctBalance + "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        DataInputStrategy strategy = new ConsoleInputStrategy();
        CustomArrayList<BankAccount> accounts;
        try {
            accounts = strategy.fill(1);
        } finally {
            System.setIn(System.in);
        }

        assertEquals(1, accounts.size());
        BankAccount retrievedAccount = accounts.get(0);
        assertEquals(correctAccountNumber, retrievedAccount.getAccountNumber());
        assertEquals(correctHolderName, retrievedAccount.getAccountHolder());
        assertEquals(correctBalance, retrievedAccount.getBalance());
    }

    @Test
    @DisplayName("Некорректное имя приводит к повторному вводу")
    void shouldAskForCorrectAccountHolder() {
        String incorrectHolderName = " ";
        String correctAccountNumber = "123456";
        String correctHolderName = "Vasya Pupkine";
        Double correctBalance = 18303.78;

        String input = correctAccountNumber + "\n" + incorrectHolderName + "\n"
                + correctBalance + "\n" + correctAccountNumber + "\n"
                + correctHolderName + "\n" + correctBalance + "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        DataInputStrategy strategy = new ConsoleInputStrategy();
        CustomArrayList<BankAccount> accounts;
        try {
            accounts = strategy.fill(1);
        } finally {
            System.setIn(System.in);
        }

        assertEquals(1, accounts.size());
        BankAccount retrievedAccount = accounts.get(0);
        assertEquals(correctAccountNumber, retrievedAccount.getAccountNumber());
        assertEquals(correctHolderName, retrievedAccount.getAccountHolder());
        assertEquals(correctBalance, retrievedAccount.getBalance());
    }

    @Test
    @DisplayName("Некорректный баланс приводит к повторному вводу")
    void shouldAskForCorrectBalance() {
        String correctAccountNumber = "123456";
        String correctHolderName = "Vasya Pupkine";
        Double correctBalance = 18303.78;
        String garbageBalance = "ijfdjlsfjs";

        String input = correctAccountNumber + "\n" + correctHolderName + "\n"
                + garbageBalance + "\n" + correctAccountNumber + "\n"
                + correctHolderName + "\n" + correctBalance + "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        DataInputStrategy strategy = new ConsoleInputStrategy();
        CustomArrayList<BankAccount> accounts;
        try {
            accounts = strategy.fill(1);
        } finally {
            System.setIn(System.in);
        }

        assertEquals(1, accounts.size());
        BankAccount retrievedAccount = accounts.get(0);
        assertEquals(correctAccountNumber, retrievedAccount.getAccountNumber());
        assertEquals(correctHolderName, retrievedAccount.getAccountHolder());
        assertEquals(correctBalance, retrievedAccount.getBalance());
    }
}