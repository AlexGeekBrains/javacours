package org.example.less2.arraylist;

public class MyArrayList<T> {
    private T[] arr;
    private int capacity;

    public MyArrayList(int size) {
        this.capacity = 0;
        this.arr = (T[]) new Object[size];
    }

    public MyArrayList(T[] init) {
        this.capacity = init.length;
        this.arr = init;
    }

    public int size() {
        return arr.length;
    }

    public T[] toArray() {
        T[] arr = (T[]) new Object[capacity];
        System.arraycopy(this.arr, 0, arr, 0, capacity);
        return arr;
    }

    public int getCapacity() {
        return capacity;
    }

    public void printArr() {
        for (int i = 0; i < this.capacity; ++i) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }

    public T get(int idx) {
        checkIndexOutsideArray(idx);
        return arr[idx];
    }

    public void set(T value, int idx) {
        checkIndexOutsideArray(idx);
        arr[idx] = value;
    }

    private void checkIndexOutsideArray(int idx) {
        if (idx >= capacity || idx < 0)
            throw new IndexOutOfBoundsException(
                    "Index: " + idx + ", out of bounds for length: " + capacity);
    }

    public boolean delete(T value) {
        for (int i = 0; i < this.capacity; i++) {
            if (this.arr[i].equals(value)) {
                System.arraycopy(this.arr, i + 1, this.arr, i, this.capacity - i - 1);
                capacity--;
                return true;
            }
        }
        return false;
    }

    public boolean deleteAll(T value) {
        boolean check = false;
        for (int i = 0; i < this.capacity; i++) {
            if (this.arr[i].equals(value)) {
                System.arraycopy(this.arr, i + 1, this.arr, i, this.capacity - i - 1);
                i--;
                check = true;
                capacity--;
            }
        }
        return check;
    }

    public boolean deleteAll() {
        capacity = 0;
        return true;
    }

    public void insert(int idx, T value) {
        checkIndexOutsideArray(idx);
        if (this.capacity == this.arr.length) {
            T[] old = this.arr;
            this.arr = (T[]) new Object[old.length * 2];
            System.arraycopy(old, 0, arr, 0, old.length);
        }
        T[] arrRight = (T[]) new Object[capacity - idx + 1];
        System.arraycopy(arr, idx, arrRight, 1, capacity - idx);
        arrRight[0] = value;
        System.arraycopy(arrRight, 0, arr, idx, arrRight.length);
        capacity++;
    }

    public void append(T value) {
        if (this.capacity == this.arr.length) {
            T[] old = this.arr;
            this.arr = (T[]) new Object[old.length * 2];
            System.arraycopy(old, 0, arr, 0, old.length);
        }
        this.arr[this.capacity++] = value;
    }

    public boolean isInArray(T value) {
        for (int i = 0; i < this.capacity; i++)
            if (this.arr[i].equals(value))
                return true;
        return false;
    }
}
