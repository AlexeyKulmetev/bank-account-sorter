package strategy.sort;

import collection.CustomArrayList;
import data.BankAccount;

public class SortByBalanceEvenStrategy implements SortingStrategy {
    @Override
    public void sort(CustomArrayList<BankAccount> accounts) {
        CustomSortUtils.sortEvenBalances(accounts);
    }
}