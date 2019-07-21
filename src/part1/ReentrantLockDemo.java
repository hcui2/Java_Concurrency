package part1;

public class ReentrantLockDemo {
    public static  void main(String[] args) throws InterruptedException{
        NonentrantLock nrelock = new NonentrantLock();

        nrelock.lock();
        System.out.println("Aquired first lock");

        System.out.println("Trying to aquire second lock");
        nrelock.lock();
        System.out.println(" Aquired Second  lock");
    }
}

class NonentrantLock {
    boolean isLocked;

    public NonentrantLock() {
        isLocked = false;
    }

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }

        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}