package part1;

public class BadSynchronization1 {
    public static void main(String[] args) throws InterruptedException{
//        Object dummyObject = new Object();
//        dummyObject.wait();

        Object dummyObject = new Object();
        Object lock = new Object();

        synchronized (lock){
            lock.notify();

            dummyObject.notify();
        }

    }
}
