package part6;

import java.util.concurrent.Executor;

public class ExecutorDemo {

    public  static void main(String[] args){
        DumbExecutor myExecutor = new DumbExecutor();
        MyTask myTask = new MyTask();
        myExecutor.execute(myTask);
    }

    static class DumbExecutor implements Executor {
        //
        public void execute(Runnable runnable){
            Thread newThread = new Thread(runnable);
            newThread.start();
        }
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println("my task is running now ...");
        }
    }
}
