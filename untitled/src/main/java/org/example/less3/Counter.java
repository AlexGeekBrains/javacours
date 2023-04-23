package org.example.less3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private final Lock lock;
    private int variable;

    public Counter() {
        lock = new ReentrantLock();
        variable = 0;
    }

    public void increment() {
        lock.lock();
        variable++;
        lock.unlock();
    }

    public int getVariable() {
        return variable;
    }
}
