package org.example.less2.linkedlist;

public class Iterator<E> {
    private Node<E> curr;
    private MyLinkedList<E> list;

    public Iterator(MyLinkedList<E> list) {
        this.list = list;
        checkCurrent();
        list.setIterator(this);
    }

    public void reset() {
        curr = list.getHead();
    }

    public boolean atEnd() {
        checkCurrent();
        return (curr.getNext() == null);
    }

    /**
     * В случае если итератор на последнем значении
     * сработает reset;
     */
    public void nextNode() {
        checkCurrent();
        if (curr.getNext() != null)
            curr = curr.getNext();
        else reset();
    }

    public Node<E> getCurrent() {
        checkCurrent();
        return curr;
    }

    public void insertAfter(E stored) {
        checkCurrent();
        Node<E> newNode = new Node<>(stored);
        if (!list.isEmpty()) {
            if (curr.getNext() != null) {
                curr.getNext().setPrevious(curr);
            } else {
                list.setTail(newNode);
            }
            newNode.setNext(curr.getNext());
            newNode.setPrevious(curr);
            curr.setNext(newNode);
        } else {
            list.setHead(newNode);
            list.setTail(newNode);
            curr = newNode;
        }
    }

    public void insertBefore(E stored) {
        checkCurrent();
        Node<E> newNode = new Node<>(stored);
        if (!list.isEmpty()) {
            if (curr.getPrevious() != null) {
                curr.getPrevious().setNext(newNode);
            } else {
                list.setHead(newNode);
            }
            newNode.setNext(curr);
            newNode.setPrevious(curr.getPrevious());
            curr.setPrevious(newNode);
        } else {
            list.setHead(newNode);
            list.setTail(newNode);
            curr = newNode;
        }
    }

    public E deleteCurrent() {
        if (list.isEmpty()) return null;
        checkCurrent();
        E stored = curr.getStored();
        if (curr.getPrevious() == null && curr.getNext() == null) {
            list.setHead(null);
            list.setTail(null);
            reset();
            return stored;
        }
        if (curr.getPrevious() == null) {
            list.setHead(curr.getNext());
            curr.getNext().setPrevious(curr.getPrevious());
            reset();
            return stored;
        }
        if (curr.getNext() == null) {
            list.setTail(curr.getPrevious());
            curr.getPrevious().setNext(curr.getNext());
            curr = curr.getPrevious();
            return stored;
        }
        curr.getPrevious().setNext(curr.getNext());
        curr.getNext().setPrevious(curr.getPrevious());
        curr = curr.getNext();
        return stored;
    }


    public void setCurr(Node<E> curr) {
        this.curr = curr;
    }

    private void checkCurrent() {
        if (curr == null) {
            reset();
        }
    }
}
