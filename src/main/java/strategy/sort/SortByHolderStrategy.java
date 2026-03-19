package strategy.sort;

import collection.CustomArrayList;
import data.BankAccount;

import java.util.Comparator;
import java.util.List;

public class SortByHolderStrategy implements SortingStrategy {
    @Override
    public void sort(CustomArrayList<BankAccount> accounts) {

        CustomSortUtils.bubbleSort(accounts, Comparator.comparing(BankAccount::getAccountHolder));
    }
}