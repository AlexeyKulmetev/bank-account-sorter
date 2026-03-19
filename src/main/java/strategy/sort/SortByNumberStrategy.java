package strategy.sort;

import collection.CustomArrayList;
import data.BankAccount;

import java.util.Comparator;

public class SortByNumberStrategy implements SortingStrategy {
    @Override
    public void sort(CustomArrayList<BankAccount> accounts) {

        CustomSortUtils.bubbleSort(accounts, Comparator.comparing(BankAccount::getAccountNumber));
    }
}