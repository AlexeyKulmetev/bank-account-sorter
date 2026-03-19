package strategy.sort;

import data.BankAccount;
import java.util.List;

public interface SortingStrategy {
    void sort(List<BankAccount> accounts);
}