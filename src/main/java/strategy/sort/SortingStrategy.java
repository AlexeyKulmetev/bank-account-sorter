package strategy.sort;

import collection.CustomArrayList;
import data.BankAccount;

/**
 * Стратегия сортировки коллекции банковских счетов.
 * Определяет алгоритм сортировки по различным критериям.
 *
 * @author Хажмурат Каиров
 */
public interface SortingStrategy {
    /**
     * Сортирует коллекцию банковских счетов в соответствии с реализованным алгоритмом.
     *
     * @param accounts коллекция счетов для сортировки
     */
    void sort(CustomArrayList<BankAccount> accounts);
}