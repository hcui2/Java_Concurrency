package part6;

import java.util.concurrent.*;

public class FutureBasicDemo {

    static ExecutorService threadPoll = Executors.newFixedThreadPool(2);
    public static void main(String[] args) throws Exception {
        System.out.println("sum: " + findSum(10));
        threadPoll.shutdown();
    }

    static int findSum (final int n) throws ExecutionException, InterruptedException {
        Callable<Integer> sumTask = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 1; i<= n; i++){
                    sum += i;
                }
                return sum;
            }
        };

        Future<Integer> f = threadPoll.submit(sumTask);
        return f.get();

    }
}
