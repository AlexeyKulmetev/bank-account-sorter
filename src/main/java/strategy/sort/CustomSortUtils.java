package strategy.sort;

import collection.CustomArrayList;

import java.util.Comparator;
import java.util.List;

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
                ///// Используем компаратор для сравнения двух соседних элементов
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    // Меняем элементы местами, если текущий больше следующего
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            ///// Если за полный проход ни один элемент не был обменян список отсортирован
            if (!swapped) {
                break;
            }
        }
    }
}

