package part6;

class ExecuteMe1 implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello World");
    }
}

class ExecuteMe2 extends Thread {
    @Override
    public void run() {
        System.out.println("Hello World");
    }
}

public class ThreadDemo {
    public static void main (String[] args){
        // anonymous inner class.
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        });
        t.start();

        ExecuteMe1 e1 = new ExecuteMe1();
        Thread t1 = new Thread(e1);
        t1.start();

        Thread t2 = new ExecuteMe2();
        t2.start();

    }
}
