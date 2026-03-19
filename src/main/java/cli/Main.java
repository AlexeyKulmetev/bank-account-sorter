package cli;

import collection.CustomArrayList;
import data.BankAccount;
import strategy.input.ConsoleInputStrategy;
import strategy.input.DataInputStrategy;
import strategy.input.RandomInputStrategy;
import strategy.sort.SortByBalanceStrategy;
import strategy.sort.SortByHolderStrategy;
import strategy.sort.SortByNumberStrategy;
import strategy.sort.SortingStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomArrayList<BankAccount> accounts = null;
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- ГЛАВНОЕ МЕНЮ ---");
            System.out.println("1. Ввод данных (Случайный или Ручной)");
            System.out.println("2. Показать текущие данные");
            System.out.println("3. Сортировать данные");
            System.out.println("4. Сохранить текущие данные в файл");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");


            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: Введите число!");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nВыберите способ ввода:");
                    System.out.println("1. Случайная генерация (Random)");
                    System.out.println("2. Ручной ввод (Console)");
                    System.out.print("Ваш выбор: ");

                    int inputChoice = scanner.nextInt();
                    System.out.print("Введите количество аккаунтов: ");
                    int count = scanner.nextInt();
                    scanner.nextLine();

                    DataInputStrategy inputStrategy = null;
                    if (inputChoice == 1) {
                        inputStrategy = new RandomInputStrategy();
                    } else if (inputChoice == 2) {
                        inputStrategy = new ConsoleInputStrategy();
                    }

                    if (inputStrategy != null) {
                        accounts = inputStrategy.fill(count);
                        System.out.println("Данные успешно загружены!");
                    } else {
                        System.out.println("Неверный способ ввода.");
                    }
                    break;

                case 2:
                    if (accounts == null || accounts.isEmpty()) {
                        System.out.println("Список пуст. Сначала создайте данные (пункт 1).");
                    } else {
                        System.out.println("\nТЕКУЩИЕ АККАУНТЫ:");
                        for (BankAccount acc : accounts) {
                            System.out.println(acc);
                        }
                    }
                    break;

                case 3:
                    if (accounts == null || accounts.isEmpty()) {
                        System.out.println("Нечего сортировать! Сначала создайте данные.");
                    } else {
                        System.out.println("\nВыберите поле для сортировки:");
                        System.out.println("1. По номеру счета");
                        System.out.println("2. По имени владельца");
                        System.out.println("3. По балансу");
                        System.out.print("Ваш выбор: ");

                        int sortChoice = scanner.nextInt();
                        scanner.nextLine();

                        SortingStrategy sortStrategy = null;
                        if (sortChoice == 1) sortStrategy = new SortByNumberStrategy();
                        else if (sortChoice == 2) sortStrategy = new SortByHolderStrategy();
                        else if (sortChoice == 3) sortStrategy = new SortByBalanceStrategy();

                        if (sortStrategy != null) {
                            sortStrategy.sort(accounts);
                            System.out.println("Сортировка выполнена. Нажмите 2 для просмотра.");
                        } else {
                            System.out.println("Неверный выбор сортировки.");
                        }
                    }
                    break;
                case 4:
                    if (accounts == null || accounts.isEmpty()) {
                        System.out.println("Нечего сохранять! Сначала создайте данные.");
                    } else {
                        System.out.print("Введите имя файла для сохранения (например, output.txt): ");
                        String filename = scanner.next();
                        InputOutput.FileExportService.saveToFile(accounts, filename);
                    }
                    break;

                case 0:
                    isRunning = false;
                    System.out.println("Выход из программы. Пока!");
                    break;

                default:
                    System.out.println("Неверный пункт меню. Попробуйте снова.");
            }
        }
        scanner.close();
    }
}
////// New PR