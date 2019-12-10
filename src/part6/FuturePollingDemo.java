package part6;

import java.util.concurrent.*;

public class FuturePollingDemo {
    static ExecutorService threadPool = Executors.newSingleThreadExecutor();

    public  static  void main(String[] args) throws Exception{
        System.out.println(pollingStatusAndCancelTask(10));
        threadPool.shutdown();
    }

    static int pollingStatusAndCancelTask(final int n) throws Exception {
        int result = -1;
        Callable<Integer> sumTask1 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(10);
                int sum = 0;
                for (int i = 1; i<= n; i++){
                    sum += i;
                }

                return sum;
            }
        };


        Callable<Void> randomTask = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(3600 * 1000);
                return null;
            }
        };

        Future<Integer> f1 = threadPool.submit(sumTask1);
        Future<Void> f2 = threadPool.submit(randomTask);

        try {
            f2.cancel(true);

            while (!f1.isDone()){
                System.out.println("Waiting for first task to complete.");
            }
            result = f1.get();
        }
        catch (ExecutionException ee){
            System.out.println("Something went wrong");
        }

        System.out.println("\n Is second canceled: " + f2.isCancelled());
        return result;
    }





}
