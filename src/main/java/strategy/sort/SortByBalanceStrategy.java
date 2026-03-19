package strategy.sort;

import data.BankAccount;
import java.util.Comparator;
import java.util.List;

public class SortByBalanceStrategy implements SortingStrategy {
    @Override
    public void sort(List<BankAccount> accounts) {

        accounts.sort(Comparator.comparingDouble(BankAccount::getBalance));
    }
}