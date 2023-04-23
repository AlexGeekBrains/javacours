package org.example.less3;

public class PinPong {
    private final Object mon;
    private volatile boolean currentStatus;

    public PinPong() {
        currentStatus = true;
        mon = new Object();
    }

    public void printPin() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!currentStatus) {
                        mon.wait();
                    }
                    System.out.println("Pin");
                    currentStatus = false;
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printPong() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentStatus) {
                        mon.wait();
                    }
                    System.out.println("Pong");
                    currentStatus = true;
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}