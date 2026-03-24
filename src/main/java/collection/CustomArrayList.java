package collection;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Кастомная реализация динамического массива.
 * Автоматически увеличивает размер при достижении предела емкости.
 *
 * @param <T> тип элементов, хранящихся в коллекции
 * @author Алексей Кульметьев
 */
public class CustomArrayList<T> implements Iterable<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Создает пустой список с начальной емкостью по умолчанию (10).
     */
    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     * При необходимости автоматически увеличивает внутренний массив.
     *
     * @param element элемент для добавления
     */
    public void add(T element) {
        if (size == elements.length) {
            // Если места нет, увеличиваем массив примерно в 1.5 раза
            int newCapacity = elements.length + (elements.length >> 1);
            elements = Arrays.copyOf(elements, newCapacity);
        }
        elements[size++] = element;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента (от 0 до size-1)
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * Заменяет элемент по указанному индексу.
     *
     * @param index индекс заменяемого элемента
     * @param element новое значение элемента
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    public void set(int index, T element) {
        checkIndex(index);
        elements[index] = element;
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return true если список не содержит элементов, иначе false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Проверяет, находится ли индекс в допустимых пределах.
     *
     * @param index проверяемый индекс
     * @throws IndexOutOfBoundsException если индекс недопустим
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
    }

    /**
     * Возвращает итератор для обхода элементов списка.
     *
     * @return итератор для обхода элементов
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                return (T) elements[currentIndex++];
            }
        };
    }
}