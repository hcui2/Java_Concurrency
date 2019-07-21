package part1;

import java.util.concurrent.CountDownLatch;

public class DeadlockDemo  {

    public static void main(String[] args){
        Deadlock deadlock = new Deadlock();
        try {
            deadlock.runTest();
        }catch (InterruptedException e){

        }

    }

}

class Deadlock {
    private int counter = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    CountDownLatch latch = new CountDownLatch(2);

    Runnable incrementer = new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 0; i< 100; i++){
                    incrementCounter();
                }
            }
            catch (InterruptedException e){

            }
        }
    };

    Runnable decrementer = new Runnable() {
        @Override
        public void run() {
            for (int i= 0; i< 100; i++){
                decrementCounter();
                System.out.println("Dectremeitng " + i );

            }
        }
    };

    public void runTest() throws InterruptedException {
        Thread thread1 = new Thread(incrementer);
        Thread thread2 = new Thread(decrementer);

        thread1.start();
        Thread.sleep(100); // sleep , make sure thread get a chance to acquire the lock.

        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Oone: " + counter);
    }

    private void decrementCounter() {
        synchronized (lock2) {
            System.out.println("Aquired lock 2");
            latch.countDown();
            synchronized (lock1){
                counter--;
            }
        }
    }

    private void incrementCounter() throws InterruptedException{
        synchronized (lock1){
            latch.countDown();
            System.out.println("Acquired lock1");
            latch.await();
            synchronized (lock2) {
                counter++;
            }
        }
    }

}