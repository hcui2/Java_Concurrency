package part4;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class DiningPhilosopherDemo {

    public static void main(String[] args) throws InterruptedException{
        DiningPhilosophers.runtest();
    }
}


class DiningPhilosophers {
    private static Random random = new Random(System.currentTimeMillis());

    private Semaphore[] forks = new Semaphore[5];

    private Semaphore maxDiners = new Semaphore(4);

    public DiningPhilosophers () {
        forks[0] = new Semaphore(1);
        forks[1] = new Semaphore(1);
        forks[2] = new Semaphore(1);
        forks[3] = new Semaphore(1);
        forks[4] = new Semaphore(1);
    }

    public void lifecycleOfPhilosopher(int id) throws InterruptedException {
        while (true){
            contemplate();
            eat(id);
        }
    }

    private void eat(int id) throws InterruptedException{

        maxDiners.acquire();

        forks[id].acquire();
        forks[(id+1) %5].acquire();

        System.out.println("Philosipher " + id + " is eating");

        forks[id].release();
        forks[(id+1)%5].release();

        maxDiners.release();
    }

    private void contemplate() throws InterruptedException{
        Thread.sleep(random.nextInt(500));
    }

    static void startPhilosopher (DiningPhilosophers dp, int id){
        try {
            dp.lifecycleOfPhilosopher(id);
        }catch (InterruptedException ie){

        }
    }
    public static void runtest() throws InterruptedException{
        final DiningPhilosophers dp = new DiningPhilosophers();

        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(dp, 0);
            }
        });

        Thread p2 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(dp, 1);
            }
        });

        Thread p3 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(dp, 2);
            }
        });

        Thread p4 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(dp, 3);
            }
        });

        Thread p5 = new Thread(new Runnable() {
            @Override
            public void run() {
                startPhilosopher(dp, 4);
            }
        });

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

        p1.join();
        p2.join();
        p3.join();
        p4.join();
        p5.join();


    }
}