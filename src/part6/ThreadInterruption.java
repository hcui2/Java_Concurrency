package part6;

public class ThreadInterruption {

    public static void main(String[] args) throws InterruptedException{
        ExecuteMe executeMe = new ExecuteMe();
        Thread innerThread = new Thread(executeMe);
        innerThread.start();

        // interrupt
        System.out.println("Main Thread sleeping at" + System.currentTimeMillis() / 1000);
        Thread.sleep(5000);
        innerThread.interrupt();
        System.out.println("Main thread exits at " + System.currentTimeMillis() / 1000);
    }

    static class ExecuteMe implements Runnable {
        public void run() {
            try {
                System.out.println("inner thread goes to sleep at " + System.currentTimeMillis() / 1000);
                Thread.sleep( 1000 * 1000 );
            }catch (InterruptedException ie){
                System.out.println("inner thread interrupted at "  + System.currentTimeMillis() / 1000);
            }
        }
    }
}
