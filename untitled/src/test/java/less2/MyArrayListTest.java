package less2;


import org.example.less2.arraylist.MyArrayList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {
    private MyArrayList<Integer> arr;

    @Before
    public void setUp() {
        arr = new MyArrayList<>(2);
    }

    @Test
    public void testAppendAndGet() {
        arr.append(1);
        arr.append(2);
        arr.append(3);
        assertEquals(1, arr.get(0));
        assertEquals(2, arr.get(1));
        assertEquals(3, arr.get(2));
    }

    @Test
    public void testSet() {
        arr.append(1);
        arr.append(2);
        arr.append(3);
        arr.set(4, 1);
        Integer[] expect = {1, 4, 3};
        assertEquals(4, arr.get(1));
        assertArrayEquals(expect, arr.toArray());
    }


    @Test
    public void testDeleteAllElements() {
        arr.append(1);
        arr.append(2);
        arr.append(3);
        arr.deleteAll();
        assertEquals(0, arr.getCapacity());
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfRange() {
        arr.append(1);
        arr.append(2);
        arr.append(3);
        arr.get(4);
    }

    @Test
    public void testConstructorWithCapacity() {
        MyArrayList<Integer> list = new MyArrayList<>(3);
        assertEquals(0, list.getCapacity());
        assertEquals(3, list.size());
    }

    @Test
    public void testConstructorWithArray() {
        Integer[] arr = {1, 2, 3};
        MyArrayList<Integer> list = new MyArrayList<>(arr);
        assertEquals(3, list.size());
        assertEquals(3, list.getCapacity());
        assertArrayEquals(arr, list.toArray());
    }

    @Test
    public void testAppend() {
        MyArrayList<String> list = new MyArrayList<>(2);
        list.append("hello");
        list.append("world");
        assertEquals(2, list.size());
        assertEquals("hello", list.get(0));
        assertEquals("world", list.get(1));
        String[] expected = {"hello", "world"};
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void testInsert() {
        MyArrayList<Character> list = new MyArrayList<>(3);
        list.append('a');
        list.append('c');
        list.insert(1, 'b');
        assertEquals(3, list.size());
        assertEquals(3, list.getCapacity());
        assertEquals('a', list.get(0));
        assertEquals('b', list.get(1));
        assertEquals('c', list.get(2));
        Character[] expected = {'a', 'b', 'c'};
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void testDelete() {
        MyArrayList<Integer> list = new MyArrayList<>(4);
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        assertTrue(list.delete(3));
        assertEquals(3, list.getCapacity());
        assertEquals(4, list.size());
        assertEquals(1, list.get(0).intValue());
        assertEquals(2, list.get(1).intValue());
        assertEquals(4, list.get(2).intValue());
        Integer[] expected = {1, 2, 4};
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void testDeleteAll() {
        MyArrayList<Integer> list = new MyArrayList<>(4);
        list.append(1);
        list.append(2);
        list.append(2);
        list.append(3);
        list.append(2);
        assertTrue(list.deleteAll(2));
        assertEquals(2, list.getCapacity());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    public void testDeleteAllNotFound() {
        MyArrayList<Integer> list = new MyArrayList<>(2);
        list.append(1);
        list.append(2);
        assertFalse(list.deleteAll(3));
        assertEquals(2, list.getCapacity());
        Integer[] expected = {1, 2};
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void testIsInArray() {
        MyArrayList<String> list = new MyArrayList<>(2);
        list.append("hello");
        list.append("world");
        assertTrue(list.isInArray("hello"));
        assertFalse(list.isInArray("foo"));
    }

    @Test
    public void testExtensibility() {
        MyArrayList<String> list = new MyArrayList<>(2);
        list.append("hello");
        list.append("world");
        list.append("!");
        assertEquals(4, list.size());
        list.append("my");
        list.insert(3, "And");
        list.append("friends");
        assertEquals(8, list.size());
        assertEquals(6, list.getCapacity());
        list.delete("hello");
        assertEquals(5, list.getCapacity());
    }
}
