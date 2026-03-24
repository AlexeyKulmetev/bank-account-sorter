package inputoutput;

import collection.CustomArrayList;
import data.BankAccount;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Сервис для экспорта коллекции банковских счетов в файл.
 * Поддерживает режим добавления (append) в существующий файл.
 *
 * @author Марат Морозов
 */
public class FileExportService {

    /**
     * Сохраняет коллекцию банковских счетов в файл в режиме добавления.
     *
     * @param accounts коллекция счетов для сохранения
     * @param filename имя файла (путь) для сохранения
     */
    public static void saveToFile(CustomArrayList<BankAccount> accounts, String filename) {
        if (accounts == null || accounts.isEmpty()) {
            System.out.println("Ошибка: Нет данных для сохранения. Сначала создайте или отсортируйте коллекцию.");
            return;
        }


        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write("--- Сохраненная коллекция ---\n");
            for (BankAccount acc : accounts) {
                writer.write(acc.toString() + "\n");
            }
            writer.write("\n"); // Пустая строка для разделения записей
            System.out.println("Данные успешно дописаны в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Произошла ошибка при записи в файл: " + e.getMessage());
        }
    }
}

