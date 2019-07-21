package part2;

public class InterruptDemo {
    public static void main(String[] agrs) throws InterruptedException{
        InterruptExample.example();
    }
}


class InterruptExample {
    static public void example () throws InterruptedException {
        final  Thread sleepyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("I am too sleepy... Let sleep for an hour");
                    Thread.sleep(1000*60*60);
                }
                catch (InterruptedException ie){
                    System.out.println("The interrupt flat is cleared: " + Thread.interrupted() + Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println("Oh someone woke me up");
                    System.out.println("The interrupt flag is set now: " + Thread.currentThread().isInterrupted() + Thread.interrupted());
                }
            }
        });

        sleepyThread.start();

        System.out.println("About to wake up the sleepy");
        sleepyThread.interrupt();
        System.out.println("Woeke the sleepy");

        sleepyThread.join();
    }
}