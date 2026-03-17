package collection;

import algorithm.BinarySearch;
import algorithm.QuickSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Универсальная коллекция на основе массива с поддержкой добавления элементов,
 * поиска, сортировки и итерации.
 */
public class CustomArray<T> implements Iterable<T> {
  // Стандартная начальная ёмкость массива
  private static final int DEFAULT_CAPACITY = 10;

  // Массив для хранения элементов (использует Object[] из‑за ограничений дженериков)
  private T[] elements;

  // Текущее количество элементов в коллекции
  private int size;

  /**
   * Конструктор по умолчанию. Создаёт массив с начальной ёмкостью DEFAULT_CAPACITY.
   * Приведение типа необходимо из‑за особенностей работы дженериков в Java.
   */
  @SuppressWarnings("unchecked")
  public CustomArray() {
    this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  /**
   * Добавляет элемент в конец коллекции. При необходимости увеличивает ёмкость массива.
   * @param element добавляемый элемент
   */
  public void add(T element) {
    ensureCapacity(); // Проверяем и при необходимости увеличиваем ёмкость
    elements[size++] = element; // Добавляем элемент и увеличиваем счётчик
  }

  /**
   * Возвращает элемент по указанному индексу.
   * @param index индекс элемента
   * @return элемент по индексу
   * @throws IndexOutOfBoundsException, если индекс вне допустимого диапазона
   */
  public T get(int index) {
    checkIndex(index); // Проверяем корректность индекса
    return elements[index];
  }

  /**
   * Возвращает текущее количество элементов в коллекции.
   * @return количество элементов
   */
  public int size() {
    return size;
  }

  /**
   * Проверяет, пуста ли коллекция.
   * @return true, если коллекция пуста, иначе false
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Возвращает первый элемент коллекции.
   * @return первый элемент (элемент с индексом 0)
   * @throws ArrayIndexOutOfBoundsException если коллекция пуста
   */
  public T getFirst() {
    return elements[0];
  }

  /**
   * Сортирует элементы коллекции с использованием алгоритма быстрой сортировки.
   * @param comparator компаратор для сравнения элементов
   */
  public void sort(Comparator<? super T> comparator) {
    QuickSort.quickSort(elements, 0, size - 1, comparator);
  }

  /**
   * Выполняет бинарный поиск элемента в отсортированной коллекции.
   * @param key искомый элемент
   * @param comparator компаратор для сравнения элементов
   * @return индекс найденного элемента или -1, если элемент не найден
   */
  public int binarySearch(T key, Comparator<? super T> comparator) {
    return BinarySearch.search(elements, key, 0, size - 1, comparator);
  }

  /**
   * Увеличивает ёмкость массива, если текущий размер достиг предела.
   * Новая ёмкость — в два раза больше текущей.
   */
  private void ensureCapacity() {
    if (size == elements.length) {
      elements = Arrays.copyOf(elements, size * 2); // Удваиваем ёмкость
    }
  }

  /**
   * Проверяет корректность индекса.
   * @param index проверяемый индекс
   * @throws IndexOutOfBoundsException, если индекс не в диапазоне [0, size-1]
   */
  private void checkIndex(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  /**
   * Возвращает итератор для последовательного обхода элементов коллекции.
   * @return итератор типа Iterator<T>
   */
  @Override
  public Iterator<T> iterator() {
    return new java.util.Iterator<>() {
      private int currentIndex = 0; // Текущий индекс для итерации

      /**
       * Проверяет наличие следующего элемента.
       * @return true, если есть следующий элемент, иначе false
       */
      @Override
      public boolean hasNext() {
        return currentIndex < size;
      }

      /**
       * Возвращает следующий элемент коллекции.
       * @return следующий элемент
       * @throws NoSuchElementException если элементов больше нет
       */
      @Override
      public T next() {
        if (currentIndex >= size)
          throw new NoSuchElementException();
        return elements[currentIndex++];
      }
    };
  }
}
