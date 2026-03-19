package strategy.sort;

import data.BankAccount;

import java.util.Comparator;
import java.util.List;

public class SortByBalanceStrategy implements SortingStrategy {
    @Override
    public void sort(List<BankAccount> accounts) {

        CustomSortUtils.bubbleSort(accounts, Comparator.comparingDouble(BankAccount::getBalance));
    }
}