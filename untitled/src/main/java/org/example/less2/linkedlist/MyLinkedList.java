package org.example.less2.linkedlist;

public class MyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private Iterator<E> iterator;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertLeft(E stored) {
        Node<E> newNode = new Node<>(stored);
        if (isEmpty()) {
            this.tail = newNode;
            checkAndResetIterator();
        } else {
            head.setPrevious(newNode);
        }
        newNode.setNext(head);
        this.head = newNode;
    }

    private void checkAndResetIterator() {
        if (iterator != null) {
            iterator.reset();
        }
    }

    public void insertRight(E stored) {
        Node<E> newNode = new Node<>(stored);
        if (isEmpty()) {
            this.head = newNode;
            checkAndResetIterator();
        } else {
            tail.setNext(newNode);
        }
        newNode.setPrevious(tail);
        this.tail = newNode;
    }

    public E removeLeft() {
        if (isEmpty()) return null;
        E stored = head.getStored();
        if (iterator != null && iterator.getCurrent() == head) {
            iterator.setCurr(head.getNext());
        }
        if (head.getNext() == null) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        return stored;
    }

    public E removeRight() {
        if (isEmpty()) return null;
        E stored = (E) tail.getStored();
        if (iterator != null && iterator.getCurrent() == tail) {
            iterator.setCurr(tail.getPrevious());
        }
        if (tail.getPrevious() == null) {
            tail = null;
            head = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
        }
        return stored;
    }

    /**
     * Если итератор находился на удаляемой позиции
     * сработает reset и итератор переместиться в начало списка
     */
    public E delete(E stored) {
        if (this.isEmpty()) return null;
        Node<E> curr = head;
        while (!curr.getStored().equals(stored)) {
            if (curr.getNext() == null)
                return null;
            else
                curr = curr.getNext();
        }
        if (head.getNext() == null) {
            head = null;
            tail = null;
            if (iterator != null) iterator.reset();
            return curr.getStored();
        }
        if (curr == head) {
            head = head.getNext();
            head.setPrevious(null);
            return curr.getStored();
        }
        if (curr == tail) {
            tail = tail.getPrevious();
            tail.setNext(null);
            return curr.getStored();
        }
        curr.getPrevious().setNext(curr.getNext());
        curr.getNext().setPrevious(curr.getPrevious());
        if (iterator != null && iterator.getCurrent() == curr) {
            iterator.reset();
        }
        return curr.getStored();
    }

    public E peekHead() {
        if (isEmpty()) return null;
        return head.getStored();
    }

    public E peekTail() {
        if (isEmpty()) return null;
        return tail.getStored();
    }

    @Override
    public String toString() {
        Node<E> current = head;
        StringBuilder sb = new StringBuilder("[ ");
        while (current != null) {
            sb.append(current);
            current = current.getNext();
            sb.append((current == null) ? " ]" : ", ");
        }
        return sb.toString();
    }

    public Node<E> getHead() {
        return head;
    }

    public void setIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public void setTail(Node<E> tail) {
        this.tail = tail;
    }
}