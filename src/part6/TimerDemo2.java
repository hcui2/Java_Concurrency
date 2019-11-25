package part6;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo2 {

    public static void main(String[] args) throws  Exception{
        Timer timer = new Timer();

        TimerTask badTask = new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException("Something bad happend");
            }
        };

        TimerTask goodTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello I am a good task");
            }
        };

        timer.schedule(badTask, 10);
        Thread.sleep(5000);
        timer.schedule(goodTask, 10);
    }
}
