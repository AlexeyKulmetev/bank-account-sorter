package strategy.input;

import collection.CustomArrayList;
import data.BankAccount;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileInputStrategy implements DataInputStrategy {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public CustomArrayList<BankAccount> fill(int length) {
        CustomArrayList<BankAccount> accounts = new CustomArrayList<>();

        System.out.print("Введите путь к файлу для чтения (например, input.txt): ");
        String filePath = scanner.next();


        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(this::parseLineToAccount)
                    .filter(account -> account != null)
                    .limit(length)
                    .forEach(accounts::add);

            System.out.println("Успешно загружено аккаунтов из файла: " + accounts.size());
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return accounts;
    }


    private BankAccount parseLineToAccount(String line) {

        String[] parts = line.split(",");
        if (parts.length != 3) {
            System.out.println("Пропущена строка неверного формата: " + line);
            return null;
        }

        try {

            return new BankAccount.Builder()
                    .setAccountNumber(parts[0].trim())
                    .setAccountHolder(parts[1].trim())
                    .setBalance(Double.parseDouble(parts[2].trim()))
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка валидации в строке '" + line + "': " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Ошибка числа в строке: " + line);
            return null;
        }
    }
}