package strategy.sort;

import collection.CustomArrayList;
import data.BankAccount;

import java.util.Comparator;

public class CustomSortUtils {

    public static <T> void bubbleSort(CustomArrayList<T> list, Comparator<T> comparator) {
        if (list == null || list.size() <= 1) {
            return;
        }

        int n = list.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void sortEvenBalances(CustomArrayList<BankAccount> list) {
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {

            long val1 = (long) list.get(i).getBalance();
            if (val1 % 2 != 0) continue;

            for (int j = i + 1; j < n; j++) {
                long val2 = (long) list.get(j).getBalance();
                if (val2 % 2 != 0) continue;
                if (list.get(i).getBalance() > list.get(j).getBalance()) {
                    BankAccount temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
}