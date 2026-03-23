package collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {

    @Test
    void testAddAndGet() {
        CustomArrayList<String> list = new CustomArrayList<>();

        list.add("A");
        list.add("B");

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    void testSet() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(1);
        list.add(2);

        list.set(1, 99);

        assertEquals(99, list.get(1));
    }

    @Test
    void testIsEmpty() {
        CustomArrayList<String> list = new CustomArrayList<>();

        assertTrue(list.isEmpty());

        list.add("X");

        assertFalse(list.isEmpty());
    }

    @Test
    void testSize() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        assertEquals(0, list.size());

        list.add(10);
        list.add(20);

        assertEquals(2, list.size());
    }

    @Test
    void testGetInvalidIndex() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void testSetInvalidIndex() {
        CustomArrayList<String> list = new CustomArrayList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "X"));
    }

    @Test
    void testResizeBeyondDefaultCapacity() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        // Add more than DEFAULT_CAPACITY (10)
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }

        assertEquals(15, list.size());

        for (int i = 0; i < 15; i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    void testIterator() {
        CustomArrayList<String> list = new CustomArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        StringBuilder result = new StringBuilder();

        for (String s : list) {
            result.append(s);
        }

        assertEquals("ABC", result.toString());
    }

    @Test
    void testIteratorHasNext() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        list.add(1);
        list.add(2);

        var iterator = list.iterator();

        assertTrue(iterator.hasNext());
        iterator.next();

        assertTrue(iterator.hasNext());
        iterator.next();

        assertFalse(iterator.hasNext());
    }

    @Test
    void testNoExceptionOnValidUsage() {
        CustomArrayList<String> list = new CustomArrayList<>();

        assertDoesNotThrow(() -> {
            list.add("A");
            list.get(0);
            list.set(0, "B");
        });
    }
}