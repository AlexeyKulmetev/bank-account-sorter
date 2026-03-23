package InputOutput;

import collection.CustomArrayList;
import data.BankAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class FileExportServiceTest {

    private BankAccount acc(String number, String holder, double balance) {
        return new BankAccount.Builder()
                .setAccountNumber(number)
                .setAccountHolder(holder)
                .setBalance(balance)
                .build();
    }

    @Test
    @DisplayName("Сохранение в файл: данные успешно записываются")
    void testSaveToFile_writesData() throws IOException {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();
        list.add(acc("11111", "A", 100));
        list.add(acc("22222", "B", 200));

        File file = File.createTempFile("test", ".txt");
        file.deleteOnExit();

        FileExportService.saveToFile(list, file.getAbsolutePath());

        String content = Files.readString(file.toPath());

        assertTrue(content.contains("11111"));
        assertTrue(content.contains("22222"));
    }

    @Test
    @DisplayName("Пустой список: файл не изменяется")
    void testSaveToFile_emptyList() throws IOException {
        CustomArrayList<BankAccount> list = new CustomArrayList<>();

        File file = File.createTempFile("test", ".txt");
        file.deleteOnExit();

        long before = file.length();

        FileExportService.saveToFile(list, file.getAbsolutePath());

        long after = file.length();

        assertEquals(before, after);
    }

    @Test
    @DisplayName("Null список: метод не падает")
    void testSaveToFile_nullList() {
        assertDoesNotThrow(() ->
                FileExportService.saveToFile(null, "dummy.txt")
        );
    }
}