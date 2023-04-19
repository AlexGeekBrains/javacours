package less2;

import org.example.less2.linkedlist.MyLinkedList;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {
    @Test
    public void testInsertLeft() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertTrue(list.isEmpty());

        list.insertLeft("A");
        assertFalse(list.isEmpty());
        assertEquals("[ A ]", list.toString());

        list.insertLeft("B");
        assertFalse(list.isEmpty());
        assertEquals("[ B, A ]", list.toString());

        list.insertLeft("C");
        assertFalse(list.isEmpty());
        assertEquals("[ C, B, A ]", list.toString());
    }

    @Test
    public void testInsertRight() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertTrue(list.isEmpty());

        list.insertRight("A");
        assertFalse(list.isEmpty());
        assertEquals("[ A ]", list.toString());

        list.insertRight("B");
        assertFalse(list.isEmpty());
        assertEquals("[ A, B ]", list.toString());

        list.insertRight("C");
        assertFalse(list.isEmpty());
        assertEquals("[ A, B, C ]", list.toString());
    }

    @Test
    public void testRemoveLeft() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertNull(list.removeLeft());

        list.insertLeft("A");
        assertEquals("A", list.removeLeft());
        assertTrue(list.isEmpty());

        list.insertLeft("B");
        list.insertLeft("C");
        assertEquals("C", list.removeLeft());
        assertFalse(list.isEmpty());
        assertEquals("[ B ]", list.toString());
    }

    @Test
    public void testRemoveRight() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertNull(list.removeRight());

        list.insertRight("A");
        assertEquals("A", list.removeRight());
        assertTrue(list.isEmpty());

        list.insertRight("B");
        list.insertRight("C");
        assertEquals("C", list.removeRight());
        assertFalse(list.isEmpty());
        assertEquals("[ B ]", list.toString());
    }

    @Test
    public void testDelete() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertNull(list.delete("A"));

        list.insertLeft("A");
        assertEquals("A", list.delete("A"));
        assertTrue(list.isEmpty());

        list.insertLeft("B");
        list.insertLeft("C");
        assertNull(list.delete("D"));
        assertEquals("B", list.delete("B"));
        assertFalse(list.isEmpty());
        assertEquals("[ C ]", list.toString());
        assertEquals("C", list.delete("C"));
        assertTrue(list.isEmpty());

        list.insertLeft("D");
        list.insertLeft("E");
        list.insertLeft("F");
        assertEquals("E", list.delete("E"));
        assertFalse(list.isEmpty());
        assertEquals("[ F, D ]", list.toString());
    }

    @Test
    public void testPeekHead() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertNull(list.peekHead());

        list.insertLeft("A");
        assertEquals("A", list.peekHead());

        list.insertLeft("B");
        assertEquals("B", list.peekHead());

        list.insertLeft("C");
        assertEquals("C", list.peekHead());
    }

    @Test
    public void testPeekTail() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertNull(list.peekTail());

        list.insertRight("A");
        assertEquals("A", list.peekTail());

        list.insertRight("B");
        assertEquals("B", list.peekTail());

        list.insertRight("C");
        assertEquals("C", list.peekTail());
    }
}
