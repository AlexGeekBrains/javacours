package org.example;

import org.example.less3.Counter;
import org.example.less3.PinPong;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        PinPong pinPong = new PinPong();

        Thread thread1 = new Thread(pinPong::printPin);
        Thread thread2 = new Thread(pinPong::printPong);

        thread1.start();
        thread2.start();

        Counter counter = new Counter();

        Thread thread3 = new Thread(() ->
        {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });


        Thread thread4 = new Thread(() ->
        {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });

        thread3.start();
        thread4.start();

        Thread.sleep(2000);
        System.out.println(counter.getVariable());
    }
}
