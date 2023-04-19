package org.example.less2.linkedlist;

public class Node<E> {
    private E stored;
    private Node<E> next;
    private Node<E> previous;

    public Node(E stored) {
        this.stored = stored;
    }

    public E getStored() {
        return stored;
    }

    @Override
    public String toString() {
        return "" + stored;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node<E> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }
}
