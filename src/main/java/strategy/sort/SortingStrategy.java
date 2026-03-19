package strategy.sort;

import collection.CustomArrayList;
import data.BankAccount;


public interface SortingStrategy {
    void sort(CustomArrayList<BankAccount> accounts);
}