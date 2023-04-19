package less2;

import org.example.less2.linkedlist.Iterator;
import org.example.less2.linkedlist.MyLinkedList;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class IteratorTest {
    private MyLinkedList<Integer> list;
    private Iterator<Integer> iterator;

    @Before
    public void setUp() {
        list = new MyLinkedList<>();
        for (int i = 1; i <= 5; i++) {
            list.insertRight(i);
        }
        iterator = new Iterator<>(list);
    }

    @Test
    public void testReset() {
        // Переводим итератор в середину списка
        for (int i = 0; i < 2; i++) {
            iterator.nextNode();
        }
        // Проверяем, что текущий элемент не равен головному
        Assertions.assertNotEquals(iterator.getCurrent(), list.getHead());

        iterator.reset();
        // Проверяем, что после reset() текущий элемент равен головному
        assertEquals(iterator.getCurrent(), list.getHead());
    }

    @Test
    public void testAtEnd() {
        // Проверяем, что итератор на последнем элементе списка
        for (int i = 0; i < 4; i++) {
            iterator.nextNode();
        }
        assertTrue(iterator.atEnd());
    }

    @Test
    public void testInsertAfter() {
        // Переводим итератор на 3 элемент
        for (int i = 0; i < 2; i++) {
            iterator.nextNode();
        }

        // Вставляем новый элемент после текущего
        iterator.insertAfter(999);

        // Проверяем, что новый элемент добавлен и является следующим за текущим
        assertEquals(iterator.getCurrent().getNext().getStored(), Integer.valueOf(999));
        assertEquals(iterator.getCurrent().getNext().getPrevious().getStored(), Integer.valueOf(3));
    }

    @Test
    public void testInsertBefore() {
        // Переводим итератор на 3 элемент
        for (int i = 0; i < 2; i++) {
            iterator.nextNode();
        }

        // Вставляем новый элемент перед текущим
        iterator.insertBefore(999);

        // Проверяем, что новый элемент добавлен и является предыдущим для текущего
        assertEquals(iterator.getCurrent().getPrevious().getStored(), Integer.valueOf(999));
        assertEquals(iterator.getCurrent().getPrevious().getNext().getStored(), Integer.valueOf(3));
    }

    @Test
    public void testDeleteCurrent() {
        // Переводим итератор на 3 элемент
        for (int i = 0; i < 2; i++) {
            iterator.nextNode();
        }
        System.out.println(iterator.getCurrent());
        System.out.println(list.toString());

        // Удаляем текущий элемент
        iterator.deleteCurrent();
        System.out.println(iterator.getCurrent());

        // Проверяем, что текущий элемент переместился на следующий и элемент был удален из списка
        assertEquals(iterator.getCurrent().getStored(), Integer.valueOf(4));
    }
}
