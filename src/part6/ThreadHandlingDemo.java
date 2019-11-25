package part6;

class ExecuteMe implements Runnable{

    @Override
    public void run() {
        while (true) {
            System.out.println("Say hello over and over again");

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {

            }
        }
    }
}
public class ThreadHandlingDemo {
    public static void  main(String[] args) throws InterruptedException{
        ExecuteMe executeMe = new ExecuteMe();
        Thread innerThread = new Thread(executeMe);

        innerThread.start();
        innerThread.join();
    }


}
