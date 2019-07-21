package part2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MissedSignalDemo {

    public static  void  main(String[] args) throws InterruptedException{
       // MissedSignalExmaple.example();
        FixedMissedSignalExample.example();
    }
}

class MissedSignalExmaple{
    public static void example() throws InterruptedException{
        final ReentrantLock lock = new ReentrantLock();

        final Condition condition =  lock.newCondition();

        Thread signaller = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                condition.signal();
                System.out.println("Sent Signal");
                lock.unlock();
            }
        });

        Thread waiter = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    condition.await();
                    System.out.println("Recieved signal");
                }catch (InterruptedException ie){

                }
                lock.unlock();
            }
        });


        signaller.start();
        signaller.join();

        waiter.start();
        waiter.join();

        System.out.println("Program exit. ");
    }
}

class FixedMissedSignalExample {
    public static void example() throws InterruptedException {
        final Semaphore semaphore = new Semaphore(1);

        Thread signaller = new Thread(new Runnable() {
            @Override
            public void run() {
                semaphore.release();
                System.out.println("Sent signal");
            }
        });

        Thread waiter = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println("Recieved signal");
                }catch (InterruptedException ie){

                }
            }
        });

        signaller.start();
        signaller.join();

        Thread.sleep(5000);
        waiter.start();
        waiter.join();
        System.out.println("Progam exit");


    }
}