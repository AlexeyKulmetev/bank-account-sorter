package strategy.input;

import collection.CustomArrayList;
import data.BankAccount;

/**
 * Стратегия заполнения коллекции банковских счетов.
 * Определяет способ получения данных (случайный, ручной, из файла).
 *
 * @author Марат Морозов
 */
public interface DataInputStrategy {
    CustomArrayList<BankAccount> fill(int length);
}